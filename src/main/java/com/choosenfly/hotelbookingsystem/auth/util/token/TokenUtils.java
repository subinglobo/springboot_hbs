package com.choosenfly.hotelbookingsystem.auth.util.token;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    private final SecureRandom secureRandom = new SecureRandom();
    private final int tokenByteSize;
    private final String serverSalt; // optional extra secret before hashing

    public TokenUtils(@Value("${app.refresh.token.byte-size:64}") int tokenByteSize,
                      @Value("${app.refresh.token.hash-salt:}") String serverSalt) {
        this.tokenByteSize = tokenByteSize;
        this.serverSalt = serverSalt == null ? "" : serverSalt;
    }

    /** Generates a cryptographically secure random token (raw), returned as Base64 URL-safe string. */
    public String generateSecureToken() {
        byte[] bytes = new byte[tokenByteSize];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    /** Hash the raw token using SHA-256 (hex). */
    public String hash(String rawToken) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String toHash = rawToken + serverSalt;
            byte[] hashed = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));
            // convert to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash token", e);
        }
    }
}