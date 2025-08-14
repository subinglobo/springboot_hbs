package com.choosenfly.hotelbookingsystem.auth.dto.otp;

import java.io.Serializable;
import java.time.Instant;

public class OtpInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String otp;
    private Instant expiry;
    private int attempts;

    // Constructors, getters, setters
    public OtpInfo() {}

    public OtpInfo(String otp, Instant expiry) {
        this.otp = otp;
        this.expiry = expiry;
        this.attempts = 0;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Instant getExpiry() {
        return expiry;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
