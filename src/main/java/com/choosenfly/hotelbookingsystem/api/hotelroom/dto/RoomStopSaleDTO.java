package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;


public class RoomStopSaleDTO {
    
    private Long hotel_id;
    private Long hotel_room_category_id;
    private Long hotel_roomtype_id;
    private Date stopSaleDate;
    private Boolean isStopSale;
    private String stopSaleType; // "CheckIn", "CheckOut", "Both"
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public Long getHotel_room_category_id() {
		return hotel_room_category_id;
	}
	public void setHotel_room_category_id(Long hotel_room_category_id) {
		this.hotel_room_category_id = hotel_room_category_id;
	}
	public Long getHotel_roomtype_id() {
		return hotel_roomtype_id;
	}
	public void setHotel_roomtype_id(Long hotel_roomtype_id) {
		this.hotel_roomtype_id = hotel_roomtype_id;
	}
	public Date getStopSaleDate() {
		return stopSaleDate;
	}
	public void setStopSaleDate(Date stopSaleDate) {
		this.stopSaleDate = stopSaleDate;
	}
	public Boolean getIsStopSale() {
		return isStopSale;
	}
	public void setIsStopSale(Boolean isStopSale) {
		this.isStopSale = isStopSale;
	}
	public String getStopSaleType() {
		return stopSaleType;
	}
	public void setStopSaleType(String stopSaleType) {
		this.stopSaleType = stopSaleType;
	}
	@Override
	public String toString() {
		return "RoomStopSaleDTO [hotel_id=" + hotel_id + ", hotel_room_category_id=" + hotel_room_category_id
				+ ", hotel_roomtype_id=" + hotel_roomtype_id + ", stopSaleDate=" + stopSaleDate + ", isStopSale="
				+ isStopSale + ", stopSaleType=" + stopSaleType + "]";
	}
    
    
}
