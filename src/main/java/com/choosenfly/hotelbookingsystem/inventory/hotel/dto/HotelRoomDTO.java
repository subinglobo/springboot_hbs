package com.choosenfly.hotelbookingsystem.inventory.hotel.dto;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.occupancy.dto.RoomOccupancyDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelRoomDTO {

	private Long id;
	private Long hotelId; // Reference to Hotel
	private Long roomCategoryId; // Reference to MasterRoomCategory
	private String roomName;
	private List<Long> amenityIds; // References to LinkedHotelRoomAmenity (simplified as IDs)
	private Long roomTypeId; // Reference to MasterRoomType
	private Boolean isDeleted;
	private Long hotelOccupancyId;
	private List<RoomOccupancyDTO> roomOccupancies;

	public Long getHotelOccupancyId() {
		return hotelOccupancyId;
	}

	public void setHotelOccupancyId(Long hotelOccupancyId) {
		this.hotelOccupancyId = hotelOccupancyId;
	}

	public List<RoomOccupancyDTO> getRoomOccupancies() {
		return roomOccupancies;
	}

	public void setRoomOccupancies(List<RoomOccupancyDTO> roomOccupancies) {
		this.roomOccupancies = roomOccupancies;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getRoomCategoryId() {
		return roomCategoryId;
	}

	public void setRoomCategoryId(Long roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<Long> getAmenityIds() {
		return amenityIds;
	}

	public void setAmenityIds(List<Long> amenityIds) {
		this.amenityIds = amenityIds;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "HotelRoomDTO [id=" + id + ", hotelId=" + hotelId + ", roomCategoryId=" + roomCategoryId + ", roomName="
				+ roomName + ", amenityIds=" + amenityIds + ", roomTypeId=" + roomTypeId + ", isDeleted=" + isDeleted
				+ ", hotelOccupancyId=" + hotelOccupancyId + ", roomOccupancies=" + roomOccupancies + "]";
	}

}