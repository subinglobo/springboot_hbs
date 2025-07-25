package com.choosenfly.hotelbookingsystem.dto.masters;

public class MasterRoomAmenityDTO  {

    private Long amenitiesId;

    private String name;

    private Boolean isDeleted;

    private String hotelRoomAmenities;

	public Long getAmenitiesId() {
		return amenitiesId;
	}

	public void setAmenitiesId(Long amenitiesId) {
		this.amenitiesId = amenitiesId;
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

	public String getHotelRoomAmenities() {
		return hotelRoomAmenities;
	}

	public void setHotelRoomAmenities(String hotelRoomAmenities) {
		this.hotelRoomAmenities = hotelRoomAmenities;
	}

	@Override
	public String toString() {
		return "MasterRoomAmenities [amenitiesId=" + amenitiesId + ", name=" + name + ", isDeleted=" + isDeleted
				+ ", hotelRoomAmenities=" + hotelRoomAmenities + "]";
	}


    
}