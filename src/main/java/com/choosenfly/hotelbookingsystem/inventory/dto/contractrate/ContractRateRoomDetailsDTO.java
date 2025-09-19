package com.choosenfly.hotelbookingsystem.inventory.dto.contractrate;

public class ContractRateRoomDetailsDTO {

    private Long hotelRoomcategoryId;
    
    private Long hotelRoomtypeId;
    
    private boolean isRefundable;
    
    private Long ocuppancytypeId;
    
    private int hotelMealId;
    
    private Double rate;

    private boolean extraBed;
    
    private boolean meal;
    
    private Double adultRate;
    
    private Double childRate;

    
	public Long getHotelRoomcategoryId() {
		return hotelRoomcategoryId;
	}


	public void setHotelRoomcategoryId(Long hotelRoomcategoryId) {
		this.hotelRoomcategoryId = hotelRoomcategoryId;
	}


	public Long getHotelRoomtypeId() {
		return hotelRoomtypeId;
	}


	public void setHotelRoomtypeId(Long hotelRoomtypeId) {
		this.hotelRoomtypeId = hotelRoomtypeId;
	}


	public boolean isRefundable() {
		return isRefundable;
	}


	public void setRefundable(boolean isRefundable) {
		this.isRefundable = isRefundable;
	}


	public Long getOcuppancytypeId() {
		return ocuppancytypeId;
	}


	public void setOcuppancytypeId(Long ocuppancytypeId) {
		this.ocuppancytypeId = ocuppancytypeId;
	}


	public int getHotelMealId() {
		return hotelMealId;
	}


	public void setHotelMealId(int hotelMealId) {
		this.hotelMealId = hotelMealId;
	}


	public Double getRate() {
		return rate;
	}


	public void setRate(Double rate) {
		this.rate = rate;
	}


	public boolean isExtraBed() {
		return extraBed;
	}


	public void setExtraBed(boolean extraBed) {
		this.extraBed = extraBed;
	}


	public boolean isMeal() {
		return meal;
	}


	public void setMeal(boolean meal) {
		this.meal = meal;
	}


	public Double getAdultRate() {
		return adultRate;
	}


	public void setAdultRate(Double adultRate) {
		this.adultRate = adultRate;
	}


	public Double getChildRate() {
		return childRate;
	}


	public void setChildRate(Double childRate) {
		this.childRate = childRate;
	}

	
	@Override
	public String toString() {
		return "ContractRateRoomDetailsDTO [hotelRoomcategoryId=" + hotelRoomcategoryId + ", hotelRoomtypeId="
				+ hotelRoomtypeId + ", isRefundable=" + isRefundable + ", ocuppancytypeId=" + ocuppancytypeId
				+ ", hotelMealId=" + hotelMealId + ", rate=" + rate + ", extraBed=" + extraBed + ", meal=" + meal
				+ ", adultRate=" + adultRate + ", childRate=" + childRate + "]";
	}
    
    
}

