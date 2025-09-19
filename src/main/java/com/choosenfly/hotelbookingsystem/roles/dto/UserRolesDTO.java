package com.choosenfly.hotelbookingsystem.roles.dto;

import jakarta.persistence.Column;

public class UserRolesDTO {
	
	private Long id;

	private String roleName;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserRolesDTO [id=" + id + ", roleName=" + roleName + ", description=" + description + "]";
	}
	
	


}
