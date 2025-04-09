package com.choosenfly.hotelbookingsystem.dto.email;

import java.io.Serializable;
import java.util.Arrays;

public class EmailDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] toEmail;
    private String username;
    private String password;
    
    public EmailDTO()
    {
    	
    }
    
	public String[] getToEmail() {
		return toEmail;
	}
	public void setToEmail(String[] toEmail) {
		this.toEmail = toEmail;
	}
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
		return "EmailDTO [toEmail=" + Arrays.toString(toEmail) + ", username=" + username + ", password=" + password
				+ "]";
	}
    
    
}
