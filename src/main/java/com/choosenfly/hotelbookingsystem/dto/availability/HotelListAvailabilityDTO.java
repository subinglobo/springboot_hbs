package com.choosenfly.hotelbookingsystem.dto.availability;

import com.choosenfly.hotelbookingsystem.entities.availability.HotelAvailability.AvailabilityType;

public class HotelListAvailabilityDTO {

	private Long availabilityId;
	
	private String marketName;
	
	private String roomCategory;
	
	private Integer noOfRooms;
	
	private AvailabilityType availabilityType;
	
	private Boolean status;

	
	
	public Long getAvailabilityId() {
		return availabilityId;
	}

	public void setAvailabilityId(Long availabilityId) {
		this.availabilityId = availabilityId;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}

	public Integer getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public AvailabilityType getAvailabilityType() {
		return availabilityType;
	}

	public void setAvailabilityType(AvailabilityType availabilityType) {
		this.availabilityType = availabilityType;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HotelListAvailabilityDTO [availabilityId=" + availabilityId + ", marketName=" + marketName
				+ ", roomCategory=" + roomCategory + ", noOfRooms=" + noOfRooms + ", availabilityType="
				+ availabilityType + ", status=" + status + "]";
	}


	
}
