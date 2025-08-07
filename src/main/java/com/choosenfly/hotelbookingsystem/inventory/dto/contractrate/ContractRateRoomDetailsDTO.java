package com.choosenfly.hotelbookingsystem.inventory.dto.contractrate;

public class ContractRateRoomDetailsDTO {

    private Long hotel_roomcategory_id;
    
    private Long hotel_roomtype_id;
    
    private boolean isrefundable;
    
    private Long ocuppancytype_id;
    
    private int hotel_meal_id;
    
    private Double rate;

    private boolean extraBed;
    
    private boolean meal;
    
    private Double adultrate;
    
    private Double childrate;

    

	
	public Long getHotel_roomcategory_id() {
		return hotel_roomcategory_id;
	}

	public void setHotel_roomcategory_id(Long hotel_roomcategory_id) {
		this.hotel_roomcategory_id = hotel_roomcategory_id;
	}

	public Long getHotel_roomtype_id() {
		return hotel_roomtype_id;
	}

	public void setHotel_roomtype_id(Long hotel_roomtype_id) {
		this.hotel_roomtype_id = hotel_roomtype_id;
	}

	public boolean isIsrefundable() {
		return isrefundable;
	}

	public void setIsrefundable(boolean isrefundable) {
		this.isrefundable = isrefundable;
	}

	public Long getOcuppancytype_id() {
		return ocuppancytype_id;
	}

	public void setOcuppancytype_id(Long ocuppancytype_id) {
		this.ocuppancytype_id = ocuppancytype_id;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public void setAdultrate(Double adultrate) {
		this.adultrate = adultrate;
	}

	public void setChildrate(Double childrate) {
		this.childrate = childrate;
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

	
	public int getHotel_meal_id() {
		return hotel_meal_id;
	}

	public void setHotel_meal_id(int hotel_meal_id) {
		this.hotel_meal_id = hotel_meal_id;
	}

	
	public Double getAdultrate() {
		return adultrate;
	}

	public Double getChildrate() {
		return childrate;
	}

	@Override
	public String toString() {
		return "ContractRateRoomDetailsDTO [hotel_roomcategory_id=" + hotel_roomcategory_id + ", hotel_roomtype_id="
				+ hotel_roomtype_id + ", isrefundable=" + isrefundable + ", ocuppancytype_id=" + ocuppancytype_id
				+ ", hotel_meal_id=" + hotel_meal_id + ", rate=" + rate + ", extraBed=" + extraBed + ", meal=" + meal
				+ ", adultrate=" + adultrate + ", childrate=" + childrate + "]";
	}
    
    
}

