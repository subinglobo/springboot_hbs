package com.choosenfly.hotelbookingsystem.inventory.dto.discount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiscountRoomDTO {

	   private Long RoomId;
	   
       private Long hotelRoomcategoryId;
       
       private Long hotelRoomtypeId;
       
       private String discountPercent;
       
       private String discountValue;
       
       private String lengthRestriction;

       

	

	public Long getRoomId() {
		return RoomId;
	}

	public void setRoomId(Long roomId) {
		RoomId = roomId;
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

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}

	public String getLengthRestriction() {
		return lengthRestriction;
	}

	public void setLengthRestriction(String lengthRestriction) {
		this.lengthRestriction = lengthRestriction;
	}
       
       
}
