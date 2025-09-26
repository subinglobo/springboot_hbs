package com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents;

public class CompulsorySupplymentsRateDTO {

    private Long supplymentrateId;
    
    private Long hotelRoomcategoryId;
    
    private Long ocuppancytypeId;
    
    private Double rate;
    
    private Double rateAdult;
    
    private Double rateChild;

    

	

	public Long getSupplymentrateId() {
		return supplymentrateId;
	}

	public void setSupplymentrateId(Long supplymentrateId) {
		this.supplymentrateId = supplymentrateId;
	}

	public Long getHotelRoomcategoryId() {
		return hotelRoomcategoryId;
	}

	public void setHotelRoomcategoryId(Long hotelRoomcategoryId) {
		this.hotelRoomcategoryId = hotelRoomcategoryId;
	}

	
	
	
	public Long getOcuppancytypeId() {
		return ocuppancytypeId;
	}

	public void setOcuppancytypeId(Long ocuppancytypeId) {
		this.ocuppancytypeId = ocuppancytypeId;
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
				+ hotelRoomcategoryId + ", ocuppancytypeId=" + ocuppancytypeId + ", rate=" + rate + ", rateAdult="
				+ rateAdult + ", rateChild=" + rateChild + "]";
	}
    
    
    
}
