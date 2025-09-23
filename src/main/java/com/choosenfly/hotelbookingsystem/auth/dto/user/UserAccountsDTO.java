package com.choosenfly.hotelbookingsystem.auth.dto.user;

public class UserAccountsDTO {

//	private Long id;
	
	private Long userId;	

	private String userName;
	
	private String password;
	
	

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

	@Override
	public String toString() {
		return "UserAccountsDTO [userId=" + userId + ", userName=" + userName + "]";
	}

	
	

}