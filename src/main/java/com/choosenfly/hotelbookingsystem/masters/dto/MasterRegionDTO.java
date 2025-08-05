package com.choosenfly.hotelbookingsystem.masters.dto;

public class MasterRegionDTO  {

	private Long id;

	private String name;

	private Boolean isDeleted = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "MasterRegion [id=" + id + ", name=" + name + ", isDeleted=" + isDeleted + "]";
	}

	// Getters and Setters

}