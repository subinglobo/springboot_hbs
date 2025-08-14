package com.choosenfly.hotelbookingsystem.auth.dto.login;

import jakarta.validation.constraints.NotBlank;

public class OTPVerifyRequest {

	@NotBlank
	private String username;
	@NotBlank
	private String otp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "OTPVerifyRequest [username=" + username + ", otp=" + otp + "]";
	}

}
