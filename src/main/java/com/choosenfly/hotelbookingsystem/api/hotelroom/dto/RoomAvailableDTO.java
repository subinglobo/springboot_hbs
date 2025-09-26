package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;


public class RoomAvailableDTO {
    
    private Long hotel_id;
    private Long hotel_room_category_id;
    private Long hotel_roomtype_id;
    private Date availableDate;
    private Integer availableRooms;
    private Boolean isAvailable;
    private String availabilityType; // "FreeSale", "Allocation", etc.
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
	public Date getAvailableDate() {
		return availableDate;
	}
	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}
	public Integer getAvailableRooms() {
		return availableRooms;
	}
	public void setAvailableRooms(Integer availableRooms) {
		this.availableRooms = availableRooms;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getAvailabilityType() {
		return availabilityType;
	}
	public void setAvailabilityType(String availabilityType) {
		this.availabilityType = availabilityType;
	}
	@Override
	public String toString() {
		return "RoomAvailableDTO [hotel_id=" + hotel_id + ", hotel_room_category_id=" + hotel_room_category_id
				+ ", hotel_roomtype_id=" + hotel_roomtype_id + ", availableDate=" + availableDate + ", availableRooms="
				+ availableRooms + ", isAvailable=" + isAvailable + ", availabilityType=" + availabilityType + "]";
	}
    
    
}
