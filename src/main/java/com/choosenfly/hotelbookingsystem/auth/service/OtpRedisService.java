package com.choosenfly.hotelbookingsystem.auth.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.auth.dto.otp.OtpInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OtpRedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final Duration OTP_TTL = Duration.ofMinutes(5); // OTP valid for 5 mins
    private static final Duration PASSWORD_LOGIN_TTL = Duration.ofDays(7); // Password valid for 7 days

    
    @Autowired
    private ObjectMapper objectMapper;
    
    public OtpRedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String getOtpKey(String username) {
        return "otp:" + username;
    }

    private String getLastPasswordLoginKey(String username) {
        return "lastPasswordLogin:" + username;
    }

    // Store OTP with expiry in Redis
    public void storeOtp(String username, String otp) {
        OtpInfo otpInfo = new OtpInfo(otp, Instant.now().plus(OTP_TTL));
        redisTemplate.opsForValue().set(getOtpKey(username), otpInfo, OTP_TTL);
    }

    // Retrieve OTP info
    public OtpInfo getOtpInfo(String username) {
        String key = getOtpKey(username);
        Object val = redisTemplate.opsForValue().get(key);

        System.out.println("OTP key: " + key);
        System.out.println("OTP val from redis: " + val);
        System.out.println("Value type: " + (val != null ? val.getClass().getName() : "null"));

        if (val == null) {
            return null;
        }

        try {
            // Convert the deserialized value (e.g., LinkedHashMap) to OtpInfo
            return objectMapper.convertValue(val, OtpInfo.class);
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to deserialize value to OtpInfo: " + e.getMessage());
            return null;
        }
    }

    // Delete OTP after verification or expiry
    public void deleteOtp(String username) {
        redisTemplate.delete(getOtpKey(username));
    }

    // Increment OTP attempts and return updated count
    public int incrementOtpAttempts(String username) {
        OtpInfo otpInfo = getOtpInfo(username);
        if (otpInfo == null) return 0;
        otpInfo.setAttempts(otpInfo.getAttempts() + 1);
        redisTemplate.opsForValue().set(getOtpKey(username), otpInfo, Duration.between(Instant.now(), otpInfo.getExpiry()));
        return otpInfo.getAttempts();
    }

    // Store last password login timestamp with TTL
    public void updateLastPasswordLogin(String username) {
        redisTemplate.opsForValue().set(getLastPasswordLoginKey(username), Instant.now(), PASSWORD_LOGIN_TTL);
    }

    // Get last password login timestamp
    public Instant getLastPasswordLogin(String username) {
        Object val = redisTemplate.opsForValue().get(getLastPasswordLoginKey(username));
        if (val instanceof Instant) {
            return (Instant) val;
        }
        return null;
    }

}