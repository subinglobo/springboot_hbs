package com.choosenfly.hotelbookingsystem.dto.user;

import java.util.Arrays;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

	private Long userId;

	private Long userTyeId;

	private String userName;

	private String[] userMailIds;

	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
	private String password;

	private String userRole;

	@NotNull
	private UserType userType;

	public String[] getUserMailIds() {
		return userMailIds;
	}

	public void setUserMailIds(String[] userMailIds) {
		this.userMailIds = userMailIds;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Long getUserTyeId() {
		return userTyeId;
	}

	public void setUserTyeId(Long userTyeId) {
		this.userTyeId = userTyeId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public enum UserType {
		SUPER_ADMIN, ADMIN, AGENT, HOTEL, EMPLOYEE
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userTyeId=" + userTyeId + ", userName=" + userName + ", userMailIds="
				+ Arrays.toString(userMailIds) + ", password=" + password + ", userRole=" + userRole + ", userType="
				+ userType + "]";
	}

}
