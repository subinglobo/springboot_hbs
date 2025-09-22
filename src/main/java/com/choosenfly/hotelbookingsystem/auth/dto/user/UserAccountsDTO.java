package com.choosenfly.hotelbookingsystem.auth.dto.user;

public class UserAccountsDTO {

	private Long id;
	
	private Long userId;	

	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return "UserAccountsDTO [id=" + id + ", userId=" + userId + ", userName=" + userName + "]";
	}
	
	

}