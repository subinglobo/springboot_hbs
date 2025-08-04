package com.choosenfly.hotelbookingsystem.masters.dto;

public class MasterContactTypeDTO  {

    private Long contacttypeId;

    private String name;

    private Boolean isDeleted;

	public Long getContacttypeId() {
		return contacttypeId;
	}

	public void setContacttypeId(Long contacttypeId) {
		this.contacttypeId = contacttypeId;
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
		return "MasterContactTypeDTO [contacttypeId=" + contacttypeId + ", name=" + name + ", isDeleted=" + isDeleted
				+ "]";
	}
	
	

    // Getters and Setters
    
}