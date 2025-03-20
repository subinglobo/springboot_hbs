package com.choosenfly.hotelbookingsystem.dto.occupancy;

import java.util.List;

import com.choosenfly.hotelbookingsystem.dto.hotel.HotelRoomDTO;

public class HotelOccupancyDTO {

	private Long id;
	private Long hotelId;
	private Long marketTypeId;
	private List<OccupancyValidityDTO> validityPeriods;
	private List<HotelRoomDTO> hotelRooms;
	private boolean deleted;
	private boolean live;
	private boolean validity;

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

	public Long getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	public List<OccupancyValidityDTO> getValidityPeriods() {
		return validityPeriods;
	}

	public void setValidityPeriods(List<OccupancyValidityDTO> validityPeriods) {
		this.validityPeriods = validityPeriods;
	}

	public List<HotelRoomDTO> getHotelRooms() {
		return hotelRooms;
	}

	public void setHotelRooms(List<HotelRoomDTO> hotelRooms) {
		this.hotelRooms = hotelRooms;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}

	@Override
	public String toString() {
		return "HotelOccupancyDTO [id=" + id + ", hotelId=" + hotelId + ", marketTypeId=" + marketTypeId
				+ ", validityPeriods=" + validityPeriods + ", hotelRooms=" + hotelRooms + ", deleted=" + deleted
				+ ", live=" + live + ", validity=" + validity + "]";
	}

}
