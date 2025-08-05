package com.choosenfly.hotelbookingsystem.masters.dto;

public class MasterRoomAmenityDTO  {

    private Long amenitiesId;

    private String roomAmenity;

    private Boolean isDeleted;

	public Long getAmenitiesId() {
		return amenitiesId;
	}

	public void setAmenitiesId(Long amenitiesId) {
		this.amenitiesId = amenitiesId;
	}

	public String getRoomAmenity() {
		return roomAmenity;
	}

	public void setRoomAmenity(String roomAmenity) {
		this.roomAmenity = roomAmenity;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "MasterRoomAmenityDTO [amenitiesId=" + amenitiesId + ", roomAmenity=" + roomAmenity + ", isDeleted="
				+ isDeleted + "]";
	}

	


    
}