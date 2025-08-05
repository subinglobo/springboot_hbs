package com.choosenfly.hotelbookingsystem.masters.dto;

import jakarta.validation.constraints.NotBlank;

public class MasterAgentCategoryDTO {

 	private Long agentCategoryId;

 	@NotBlank(message = "cannot be null or empty")
    private String name;

    private Boolean isDeleted;

	public Long getAgentCategoryId() {
		return agentCategoryId;
	}

	public void setAgentCategoryId(Long agentCategoryId) {
		this.agentCategoryId = agentCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "MasterAgentCategoryDTO [agentCategoryId=" + agentCategoryId + ", name=" + name + ", isDeleted="
				+ isDeleted + "]";
	}
     
    
}
