package com.choosenfly.hotelbookingsystem.auth.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
	
	@NotBlank(message = "User name cannot be empty")
	@NotNull(message = "User name is required")
    private String username;
	
	@NotBlank(message = "Password cannot be empty")
	@NotNull(message = "Password is required")
    private String password;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + "]";
	}
    
    
}