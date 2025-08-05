package com.choosenfly.hotelbookingsystem.masters.dto;

public class MasterHotelAmenityDTO  {

    private Long amenitiesId;

    private String amenityName;

    private Boolean isDeleted;

    // Getters and Setters
    public Long getAmenitiesId() {
        return amenitiesId;
    }

    public void setAmenitiesId(Long amenitiesId) {
        this.amenitiesId = amenitiesId;
    }

    public String getAmenityName() {
		return amenityName;
	}

	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}

	public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}