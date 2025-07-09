package com.choosenfly.hotelbookingsystem.dto.masters;

public class MasterDesignationDTO {

    private Long designationId;

    private String name;

    private Boolean isDeleted;

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
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
		return "MasterDesignationDTO [designationId=" + designationId + ", name=" + name + ", isDeleted=" + isDeleted
				+ "]";
	}

	
    
}