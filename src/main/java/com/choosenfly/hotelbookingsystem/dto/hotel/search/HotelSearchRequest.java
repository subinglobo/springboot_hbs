package com.choosenfly.hotelbookingsystem.dto.hotel.search;

import java.util.List;

public class HotelSearchRequest {

	private String checkIn;
    private String checkOut;
    private String nationalityId;
    private String noOfRooms;
    private int destinationCityId;
    private int destinationCountryId;
    private Long agentId;
    private List<RoomConfiguration> roomConfigurations;
    
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public String getNationalityId() {
		return nationalityId;
	}
	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}
	public String getNoOfRooms() {
		return noOfRooms;
	}
	public void setNoOfRooms(String noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	public int getDestinationCityId() {
		return destinationCityId;
	}
	public void setDestinationCityId(int destinationCityId) {
		this.destinationCityId = destinationCityId;
	}
	public int getDestinationCountryId() {
		return destinationCountryId;
	}
	public void setDestinationCountryId(int destinationCountryId) {
		this.destinationCountryId = destinationCountryId;
	}
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public List<RoomConfiguration> getRoomConfigurations() {
		return roomConfigurations;
	}
	public void setRoomConfigurations(List<RoomConfiguration> roomConfigurations) {
		this.roomConfigurations = roomConfigurations;
	}
	@Override
	public String toString() {
		return "HotelSearchRequest [checkIn=" + checkIn + ", checkOut=" + checkOut + ", nationalityId=" + nationalityId
				+ ", noOfRooms=" + noOfRooms + ", destinationCityId=" + destinationCityId + ", destinationCountryId="
				+ destinationCountryId + ", agentId=" + agentId + "]";
	}
    
    
}
