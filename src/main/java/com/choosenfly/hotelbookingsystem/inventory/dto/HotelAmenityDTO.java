package com.choosenfly.hotelbookingsystem.inventory.dto;

public class HotelAmenityDTO {
	
	private Long amenityId;
	
	private String amenityName;

	public Long getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}

	public String getAmenityName() {
		return amenityName;
	}

	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}

	@Override
	public String toString() {
		return "HotelAmenityDTO [amenityId=" + amenityId + ", amenityName=" + amenityName + "]";
	}
	
	

}
