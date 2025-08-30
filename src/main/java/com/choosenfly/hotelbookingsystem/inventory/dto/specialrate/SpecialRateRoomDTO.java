package com.choosenfly.hotelbookingsystem.inventory.dto.specialrate;

public class SpecialRateRoomDTO {

	private Long specialRateRoomId;
	
    private Long hotelRoomcategoryId;
    
    private Long hotelRoomTypeId;
    
    private Long ocuppancyTypeIid;
    
    private Double rate;
    
//    private Long hotelMealId;
    
    private Boolean extraBed;
    
//    private Boolean meal;
    
    private Double adultrate;
    
    private Double childrate;

	public Long getSpecialRateRoomId() {
		return specialRateRoomId;
	}

	public void setSpecialRateRoomId(Long specialRateRoomId) {
		this.specialRateRoomId = specialRateRoomId;
	}

	public Long getHotelRoomcategoryId() {
		return hotelRoomcategoryId;
	}

	public void setHotelRoomcategoryId(Long hotelRoomcategoryId) {
		this.hotelRoomcategoryId = hotelRoomcategoryId;
	}

	public Long getHotelRoomTypeId() {
		return hotelRoomTypeId;
	}

	public void setHotelRoomTypeId(Long hotelRoomTypeId) {
		this.hotelRoomTypeId = hotelRoomTypeId;
	}

	public Long getOcuppancyTypeIid() {
		return ocuppancyTypeIid;
	}

	public void setOcuppancyTypeIid(Long ocuppancyTypeIid) {
		this.ocuppancyTypeIid = ocuppancyTypeIid;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	

	public Boolean getExtraBed() {
		return extraBed;
	}

	public void setExtraBed(Boolean extraBed) {
		this.extraBed = extraBed;
	}

	public Double getAdultrate() {
		return adultrate;
	}

	public void setAdultrate(Double adultrate) {
		this.adultrate = adultrate;
	}

	public Double getChildrate() {
		return childrate;
	}

	public void setChildrate(Double childrate) {
		this.childrate = childrate;
	}

	
	@Override
	public String toString() {
		return "SpecialRateRoomDTO [specialRateRoomId=" + specialRateRoomId + ", hotelRoomcategoryId="
				+ hotelRoomcategoryId + ", hotelRoomTypeId=" + hotelRoomTypeId + ", ocuppancyTypeIid="
				+ ocuppancyTypeIid + ", rate=" + rate + ", extraBed=" + extraBed + ", adultrate=" + adultrate
				+ ", childrate=" + childrate + "]";
	}
    
    
    
}
