package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


public class FilterHotelRoomsDTO {
    
    private Long hotel_room_category_id;
    private Long hotel_roomtype_id;
    private Long roomType_Id;
    private Long room_category_id;
    private String roomCategory;
    private String roomType;
    private Long hotel_id;
    private String roomdetails; // Format: "hotelId~hotel_room_category_id~hotel_roomType_id"
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
	public Long getRoomType_Id() {
		return roomType_Id;
	}
	public void setRoomType_Id(Long roomType_Id) {
		this.roomType_Id = roomType_Id;
	}
	public Long getRoom_category_id() {
		return room_category_id;
	}
	public void setRoom_category_id(Long room_category_id) {
		this.room_category_id = room_category_id;
	}
	public String getRoomCategory() {
		return roomCategory;
	}
	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getRoomdetails() {
		return roomdetails;
	}
	public void setRoomdetails(String roomdetails) {
		this.roomdetails = roomdetails;
	}
	@Override
	public String toString() {
		return "FilterHotelRoomsDTO [hotel_room_category_id=" + hotel_room_category_id + ", hotel_roomtype_id="
				+ hotel_roomtype_id + ", roomType_Id=" + roomType_Id + ", room_category_id=" + room_category_id
				+ ", roomCategory=" + roomCategory + ", roomType=" + roomType + ", hotel_id=" + hotel_id
				+ ", roomdetails=" + roomdetails + "]";
	}
    
    
}
