package com.choosenfly.hotelbookingsystem.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingCredentialsException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.UserRegistrationException;
import com.choosenfly.hotelbookingsystem.auth.service.user.UserAccountServiceInterface;
import com.choosenfly.hotelbookingsystem.auth.util.jwt.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountServiceInterface userAccountService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
   
    @Autowired
    public AuthController(UserAccountServiceInterface userAccountService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userAccountService = userAccountService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO user) {
        return userAccountService.registerUser(user);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid  @RequestBody(required = true) LoginRequest request) {
        // Validate request
       

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Get user roles from authenticated principal
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(auth -> auth.getAuthority())
                    .toList();

           
            // Generate JWT token
            String token = jwtUtil.generateToken(request.getUsername(), roles);

            // Build response
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setUsername(request.getUsername());
            response.setRoles(roles);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            throw new MissingCredentialsException("Invalid username or password");
        } catch (LockedException e) {
            throw new UserRegistrationException("Account is locked");
        } catch (DisabledException e) {
            throw new UserRegistrationException("Account is disabled");
        }
    }

}
