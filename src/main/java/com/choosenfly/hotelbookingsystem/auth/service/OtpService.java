package com.choosenfly.hotelbookingsystem.auth.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.auth.exceptions.OTPException;

@Service
public class OtpService {

    private final ConcurrentHashMap<String, OTPDetails> otpStore = new ConcurrentHashMap<>();

    private static class OTPDetails {
        String otp;
        Instant expiresAt;
        OTPDetails(String otp, Instant expiresAt) {
            this.otp = otp;
            this.expiresAt = expiresAt;
        }
    }

    public String generateOtpForUser(String username) {
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        Instant expiry = Instant.now().plus(5, ChronoUnit.MINUTES);
        otpStore.put(username, new OTPDetails(otp, expiry));
        return otp;
    }

    public void validateOtp(String username, String otp) {
        OTPDetails details = otpStore.get(username);
        if (details == null) {
            throw new OTPException("No OTP found or expired");
        }
        if (details.expiresAt.isBefore(Instant.now())) {
            otpStore.remove(username);
            throw new OTPException("OTP expired");
        }
        if (!details.otp.equals(otp)) {
            throw new OTPException("Invalid OTP");
        }
        otpStore.remove(username);
    }
}
