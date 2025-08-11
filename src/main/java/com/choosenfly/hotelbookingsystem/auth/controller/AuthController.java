package com.choosenfly.hotelbookingsystem.auth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.auth.dto.login.LoginRequest;
import com.choosenfly.hotelbookingsystem.auth.dto.login.LoginResponse;
import com.choosenfly.hotelbookingsystem.auth.dto.user.UserDTO;
import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserAccount;
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingCredentialsException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.UserRegistrationException;
import com.choosenfly.hotelbookingsystem.auth.repository.user.UserAccountRepository;
import com.choosenfly.hotelbookingsystem.auth.service.token.RefreshTokenService;
import com.choosenfly.hotelbookingsystem.auth.service.user.UserAccountServiceInterface;
import com.choosenfly.hotelbookingsystem.auth.util.jwt.CustomUserDetailsService;
import com.choosenfly.hotelbookingsystem.auth.util.jwt.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountServiceInterface userAccountService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;
    private final UserAccountRepository userRepo;

    @Value("${app.refresh.cookie.name:refreshToken}")
    private String refreshCookieName;

    @Value("${app.refresh.cookie.secure:true}")
    private boolean cookieSecure;

    @Value("${app.refresh.cookie.max-age-seconds:604800}")
    private int cookieMaxAge;

    @Autowired
    public AuthController(UserAccountServiceInterface userAccountService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService,
                          RefreshTokenService refreshTokenService,
                          UserAccountRepository userRepo) {
        this.userAccountService = userAccountService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.refreshTokenService = refreshTokenService;
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO user) {
        return userAccountService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(auth -> auth.getAuthority())
                    .toList();

            String accessToken = jwtUtil.generateAccessToken(request.getUsername(), roles);

            // create & persist refresh token (raw)
            UserAccount userEntity = userRepo.findByUsername(request.getUsername()).orElseThrow();
            String rawRefreshToken = refreshTokenService.createRefreshToken(userEntity,
                    getClientIp(), request.getUsername());

            // set HttpOnly cookie (refresh token)
            Cookie cookie = new Cookie(refreshCookieName, rawRefreshToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(cookieSecure); // set false for local http testing if needed
            cookie.setPath("/");
            cookie.setMaxAge(cookieMaxAge);
            // Optionally set SameSite via header since Cookie API lacks direct support
            response.addCookie(cookie);

            // Optionally return refresh token in body (not recommended). Here we return tokens:
            LoginResponse lr = new LoginResponse();
            lr.setToken(accessToken);
            // Do not set refreshToken in body to encourage cookie usage; but if you want, set it.
            lr.setUsername(request.getUsername());
            lr.setRoles(roles);

            return ResponseEntity.ok(lr);

        } catch (BadCredentialsException e) {
            throw new MissingCredentialsException("Invalid username or password");
        } catch (LockedException e) {
            throw new UserRegistrationException("Account is locked");
        } catch (DisabledException e) {
            throw new UserRegistrationException("Account is disabled");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No refresh token cookie found");
        }

        String rawRefreshToken = null;
        for (Cookie c : cookies) {
            if (refreshCookieName.equals(c.getName())) {
                rawRefreshToken = c.getValue();
                break;
            }
        }

        if (rawRefreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token missing");
        }

        try {
            // validate raw token exists & not expired
            var optToken = refreshTokenService.validateRawToken(rawRefreshToken);
            if (optToken.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
            }

            var tokenEntity = optToken.get();
            var user = tokenEntity.getUser();

            // load roles from DB
            var userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(auth -> auth.getAuthority())
                    .toList();

            // rotate refresh token: old -> new
            String newRawRefresh = refreshTokenService.rotate(rawRefreshToken, getClientIpFromRequest(request),
                    request.getHeader("User-Agent"));

            // set new cookie
            Cookie cookie = new Cookie(refreshCookieName, newRawRefresh);
            cookie.setHttpOnly(true);
            cookie.setSecure(cookieSecure);
            cookie.setPath("/");
            cookie.setMaxAge(cookieMaxAge);
            response.addCookie(cookie);

            // create new access token
            String newAccessToken = jwtUtil.generateAccessToken(user.getUsername(), roles);

            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));

        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token invalid");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

    // helper - get client ip (simple). Replace with your existing util if present.
    private String getClientIpFromRequest(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    // lightweight stub (when HttpServletRequest not available) - you may replace or remove
    private String getClientIp() {
        return "127.0.0.1";
    }
}