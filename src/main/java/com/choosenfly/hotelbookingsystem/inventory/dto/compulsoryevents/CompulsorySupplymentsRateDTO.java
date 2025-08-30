package com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents;

public class CompulsorySupplymentsRateDTO {

    private String supplymentrateId;
    
    private Long hotelRoomcategoryId;
    
    private Long ocuppancytype_id;
    
    private Double rate;
    
    private Double rateAdult;
    
    private Double rateChild;

	public String getSupplymentrateId() {
		return supplymentrateId;
	}

	public void setSupplymentrateId(String supplymentrateId) {
		this.supplymentrateId = supplymentrateId;
	}

	

	public Long getHotelRoomcategoryId() {
		return hotelRoomcategoryId;
	}

	public void setHotelRoomcategoryId(Long hotelRoomcategoryId) {
		this.hotelRoomcategoryId = hotelRoomcategoryId;
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

	public Double getRateAdult() {
		return rateAdult;
	}

	public void setRateAdult(Double rateAdult) {
		this.rateAdult = rateAdult;
	}

	public Double getRateChild() {
		return rateChild;
	}

	public void setRateChild(Double rateChild) {
		this.rateChild = rateChild;
	}

	@Override
	public String toString() {
		return "CompulsorySupplymentsRateDTO [supplymentrateId=" + supplymentrateId + ", hotelRoomcategoryId="
				+ hotelRoomcategoryId + ", ocuppancytype_id=" + ocuppancytype_id + ", rate=" + rate + ", rateAdult="
				+ rateAdult + ", rateChild=" + rateChild + "]";
	}
    
    
    
}
