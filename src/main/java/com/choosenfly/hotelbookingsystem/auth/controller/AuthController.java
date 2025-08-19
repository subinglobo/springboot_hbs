package com.choosenfly.hotelbookingsystem.auth.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.agent.entity.Agent;
import com.choosenfly.hotelbookingsystem.agent.repository.AgentRepository;
import com.choosenfly.hotelbookingsystem.auth.dto.login.LoginRequest;
import com.choosenfly.hotelbookingsystem.auth.dto.login.LoginResponse;
import com.choosenfly.hotelbookingsystem.auth.dto.login.OTPVerifyRequest;
import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserAccount;
import com.choosenfly.hotelbookingsystem.auth.repository.user.UserAccountRepository;
import com.choosenfly.hotelbookingsystem.auth.service.OtpRedisService;
import com.choosenfly.hotelbookingsystem.auth.service.token.RefreshTokenService;
import com.choosenfly.hotelbookingsystem.auth.service.user.UserAccountServiceInterface;
import com.choosenfly.hotelbookingsystem.auth.util.jwt.CustomUserDetailsService;
import com.choosenfly.hotelbookingsystem.auth.util.jwt.JwtUtil;
import com.choosenfly.hotelbookingsystem.email.service.EmailService;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelContactDetails;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserAccountServiceInterface userAccountService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;
	private final RefreshTokenService refreshTokenService;
	private final UserAccountRepository userRepo;
	private final OtpRedisService otpRedisService;
	private final EmailService emailService;
	private final AgentRepository agentRepository;
	private final HotelRepository hotelRepository;

	@Value("${app.refresh.cookie.name:refreshToken}")
	private String refreshCookieName;

	@Value("${app.refresh.cookie.secure:true}")
	private boolean cookieSecure;

	@Value("${app.refresh.cookie.max-age-seconds:604800}")
	private int cookieMaxAge;

	@Value("${app.otp.max-attempts:5}")
	private int maxOtpAttempts;

	public AuthController(UserAccountServiceInterface userAccountService, AuthenticationManager authenticationManager,
			JwtUtil jwtUtil, CustomUserDetailsService userDetailsService, RefreshTokenService refreshTokenService,
			UserAccountRepository userRepo, OtpRedisService otpRedisService, EmailService emailService,
			AgentRepository agentRepository, HotelRepository hotelRepository) {
		this.userAccountService = userAccountService;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.refreshTokenService = refreshTokenService;
		this.userRepo = userRepo;
		this.otpRedisService = otpRedisService;
		this.emailService = emailService;
		this.agentRepository = agentRepository;
		this.hotelRepository = hotelRepository;
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody LoginRequest user) {
		return ResponseEntity.ok(userAccountService.registerUser(null)); // adapt to your existing logic
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
		
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		Instant now = Instant.now();
		Instant lastPwdLogin = otpRedisService.getLastPasswordLogin(request.getUsername());

		if (lastPwdLogin == null || lastPwdLogin.isBefore(now.minus(7, ChronoUnit.DAYS))) {
			// Need OTP verification
			String otp = String.format("%06d", (int) (Math.random() * 1_000_000));
			otpRedisService.storeOtp(request.getUsername(), otp);
			
			String[] userEmail = getUserEmail(request.getUsername());
			
			System.err.println("Mail Id :: "+Arrays.toString(userEmail));
			
			emailService.sendOtpEmail(getUserEmail(request.getUsername()), otp);

			return ResponseEntity
					.ok(Map.of("otpRequired", true, "message", "OTP sent to your registered email address."));
		}

		return completeLoginFlow(request.getUsername(), response);
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody OTPVerifyRequest request, HttpServletResponse response) {
		var otpInfo = otpRedisService.getOtpInfo(request.getUsername());

		if (otpInfo == null || otpInfo.getExpiry().isBefore(Instant.now())) {
			return ResponseEntity.badRequest().body(Map.of("error", "OTP expired or not found."));
		}

		int attempts = otpRedisService.incrementOtpAttempts(request.getUsername());
		if (attempts > maxOtpAttempts) {
			otpRedisService.deleteOtp(request.getUsername());
			return ResponseEntity.badRequest().body(Map.of("error", "Too many invalid attempts. OTP blocked."));
		}

		if (!otpInfo.getOtp().equals(request.getOtp())) {
			return ResponseEntity.badRequest().body(Map.of("error", "Invalid OTP."));
		}

		otpRedisService.deleteOtp(request.getUsername());
		otpRedisService.updateLastPasswordLogin(request.getUsername());

		return completeLoginFlow(request.getUsername(), response);
	}

	// ---------------------- Private Helpers ----------------------

	private ResponseEntity<LoginResponse> completeLoginFlow(String username, HttpServletResponse response) {
		var userDetails = userDetailsService.loadUserByUsername(username);
		List<String> roles = userDetails.getAuthorities().stream().map(auth -> auth.getAuthority()).toList();

		String accessToken = jwtUtil.generateAccessToken(username, roles);
		UserAccount userEntity = userRepo.findByUsername(username).orElseThrow();
		String rawRefreshToken = refreshTokenService.createRefreshToken(userEntity, getClientIp(), username);

		Cookie cookie = new Cookie(refreshCookieName, rawRefreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(cookieSecure);
		cookie.setPath("/");
		cookie.setMaxAge(cookieMaxAge);
		response.addCookie(cookie);

		LoginResponse lr = new LoginResponse();
		lr.setToken(accessToken);
		lr.setUsername(username);
		lr.setRoles(roles);
		lr.setOtpRequired(false);

		return ResponseEntity.ok(lr);
	}

	private String getClientIp() {
		return "127.0.0.1"; // TODO: extract from HttpServletRequest if needed
	}

	private String[] getUserEmail(String username) {
	    try {
	        UserAccount userAccount = userRepo.findByUsername(username)
	                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

	        String typeName = userAccount.getUserType().getTypeName();

	        
	        System.err.println("Type Name: "+typeName);
	        
	        
	        switch (typeName.toUpperCase()) {
	            case "AGENT":
	                return agentRepository.findById(userAccount.getUserId())
	                        .map(Agent::getPersonalEmail)
	                        .map(email -> new String[]{ email })
	                        .orElse(new String[0]); // empty array if no email found

	            case "EXTRANET":
	                return hotelRepository.findById(userAccount.getUserId())
	                        .map(Hotel::getContactDetails)
	                        .stream()
	                        .flatMap(List::stream)
	                        .map(HotelContactDetails::getPersonalEmail)
	                        .toArray(String[]::new);

	            default:
	                System.err.println("Unsupported user type: " + typeName);
	                return new String[0];
	        }
	    } catch (Exception e) {
	        System.err.println("Error while fetching user email for username: " + username);
	        e.printStackTrace();
	        return new String[0]; // fallback to empty array if anything fails
	    }
	}


}
