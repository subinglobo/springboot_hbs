package com.choosenfly.hotelbookingsystem.inventory.dto.staypay;

public class StaypayRoomDTO {

    private Long promoRoomId;
    
    private Long hotelRoomcategoryId;
    
    private Long hotelRoomtypeId;
    
    private Long noOffree;
    
    private Long noOfpay;
    
    private Long noOfstay;

    
	

	public Long getPromoRoomId() {
		return promoRoomId;
	}

	public void setPromoRoomId(Long promoRoomId) {
		this.promoRoomId = promoRoomId;
	}

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

	public Long getNoOffree() {
		return noOffree;
	}

	public void setNoOffree(Long noOffree) {
		this.noOffree = noOffree;
	}

	public Long getNoOfpay() {
		return noOfpay;
	}

	public void setNoOfpay(Long noOfpay) {
		this.noOfpay = noOfpay;
	}

	public Long getNoOfstay() {
		return noOfstay;
	}

	public void setNoOfstay(Long noOfstay) {
		this.noOfstay = noOfstay;
	}

	
	@Override
	public String toString() {
		return "StaypayRoomDTO [promoRoomId=" + promoRoomId + ", hotelRoomcategoryId=" + hotelRoomcategoryId
				+ ", hotelRoomtypeId=" + hotelRoomtypeId + ", noOffree=" + noOffree + ", noOfpay=" + noOfpay
				+ ", noOfstay=" + noOfstay + "]";
	}
    
    
}
