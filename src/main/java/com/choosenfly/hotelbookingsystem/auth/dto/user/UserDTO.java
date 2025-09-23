package com.choosenfly.hotelbookingsystem.auth.dto.user;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@NotNull(message = "User Id is required")
	private Long userId;  //corresponding agentId

	@NotNull(message = "User type is required")
	private Long userTypeId;

	@NotBlank(message = "User name cannot be empty")
	@NotNull(message = "User name is required")
	private String userName;


	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
	private String password;

	private List<Long> userRoleIds;
	
	
	private String[] userMailIds;
	

	public String[] getUserMailIds() {
		return userMailIds;
	}

	public void setUserMailIds(String[] userMailIds) {
		this.userMailIds = userMailIds;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
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

	public List<Long> getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(List<Long> userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userTypeId=" + userTypeId + ", userName=" + userName + ", password="
				+ password + ", userRoleIds=" + userRoleIds + ", userMailIds=" + Arrays.toString(userMailIds) + "]";
	}

	

	

	
	

	

}
