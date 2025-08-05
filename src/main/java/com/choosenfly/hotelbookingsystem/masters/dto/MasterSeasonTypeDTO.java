package com.choosenfly.hotelbookingsystem.masters.dto;

public class MasterSeasonTypeDTO {

	private Long seasonTypeId;

    private String season;

    private Boolean isDeleted;

	public Long getSeasonTypeId() {
		return seasonTypeId;
	}

	public void setSeasonTypeId(Long seasonTypeId) {
		this.seasonTypeId = seasonTypeId;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "MasterSeasonTypeDTO [seasonTypeId=" + seasonTypeId + ", season=" + season + ", isDeleted=" + isDeleted
				+ "]";
	}

	
	
    
}