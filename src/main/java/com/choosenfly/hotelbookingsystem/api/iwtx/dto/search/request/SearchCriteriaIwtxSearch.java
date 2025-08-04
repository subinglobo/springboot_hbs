package com.choosenfly.hotelbookingsystem.api.iwtx.dto.search.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SearchCriteria")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchCriteriaIwtxSearch {

	
	private RoomConfigurationiwtxSearch RoomConfiguration;
	
	private String StartDate;
	
	private String EndDate;
	
	private String HotelCode;

	private String Nationality;
	
	private String GroupByRooms;
	
	private String CancellationPolicy;
	
	public RoomConfigurationiwtxSearch getRoomConfiguration() {
		return RoomConfiguration;
	}
	public void setRoomConfiguration(RoomConfigurationiwtxSearch roomConfiguration) {
		RoomConfiguration = roomConfiguration;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public String getHotelCode() {
		return HotelCode;
	}
	public void setHotelCode(String hotelCode) {
		HotelCode = hotelCode;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getGroupByRooms() {
		return GroupByRooms;
	}
	public void setGroupByRooms(String groupByRooms) {
		GroupByRooms = groupByRooms;
	}
	public String getCancellationPolicy() {
		return CancellationPolicy;
	}
	public void setCancellationPolicy(String cancellationPolicy) {
		CancellationPolicy = cancellationPolicy;
	}
	
	
	
}
