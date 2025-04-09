package com.choosenfly.hotelbookingsystem.dto.occupancy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelOccupancyResponseDTO {

	
	private Long marketTypeId;
	
	private String marketName;
	
	private List<OccupancyValidityDTO> validityList;
	
	private List<RoomOccupancyDTO> rooms;

	public Long getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public List<OccupancyValidityDTO> getValidityList() {
		return validityList;
	}

	public void setValidityList(List<OccupancyValidityDTO> validityList) {
		this.validityList = validityList;
	}

	

	public List<RoomOccupancyDTO> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomOccupancyDTO> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "HotelOccupancyResponseDTO [marketTypeId=" + marketTypeId + ", marketName=" + marketName
				+ ", validityList=" + validityList + ", rooms=" + rooms + "]";
	}

	
	
	
}
