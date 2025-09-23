package com.choosenfly.hotelbookingsystem.auth.dto.user;

import java.util.Set;

public class UserAccountsDTO {

//	private Long id;
	
	private Long userId;	

	private String userName;
	
	private String password;
	
	private Set<String> userRoles; 
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "UserAccountsDTO [userId=" + userId + ", userName=" + userName + ", password=" + password
				+ ", userRoles=" + userRoles + "]";
	}

	
	
	

}