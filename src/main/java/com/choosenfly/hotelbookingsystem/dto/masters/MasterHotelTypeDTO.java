package com.choosenfly.hotelbookingsystem.dto.masters;

public class MasterHotelTypeDTO  {

    private Long hotelTypeId;

    private String name;

    private Boolean isDeleted;

	public Long getHotelTypeId() {
		return hotelTypeId;
	}

	public void setHotelTypeId(Long hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
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
		return "MasterHotelTypeDTO [hotelTypeId=" + hotelTypeId + ", name=" + name + ", isDeleted=" + isDeleted + "]";
	}

    // Getters and Setters
    
}