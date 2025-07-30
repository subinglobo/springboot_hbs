package com.choosenfly.hotelbookingsystem.dto.iwtx;

import java.util.Arrays;
import java.util.List;

public class IwtxDTO {

	
	private String startDate;
	
	private String endDate;
	
	private String hotelCode;
	
	private List<String> hotelCodes;
	
	private String nationality;
	
	private String includeRateDetails;
	
	private String includeOnRequest;
	
	private String cancellationPolicy;
	
	private String groupByRooms;
	
	private IwtxRoomDTO[] room;
	
	private List<SearchRoomDTO> searchRoomDTOs;
	
	private int source;
	
	private int bookingNumber;
	
	private String subResNo;
	
	private String agencyRef;

	private int bookingDateFrom;
	
    private int bookingDateTo;
	
    private String departureDateFrom;
	
    private String departureDateTo;

    private String arrivalDateFrom;
	
    private String arrivalDateTo;
	
    private String stayDateFrom;
	
    private String stayDateTo;
    
    private long agentId;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public List<String> getHotelCodes() {
		return hotelCodes;
	}

	public void setHotelCodes(List<String> hotelCodes) {
		this.hotelCodes = hotelCodes;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIncludeRateDetails() {
		return includeRateDetails;
	}

	public void setIncludeRateDetails(String includeRateDetails) {
		this.includeRateDetails = includeRateDetails;
	}

	public String getIncludeOnRequest() {
		return includeOnRequest;
	}

	public void setIncludeOnRequest(String includeOnRequest) {
		this.includeOnRequest = includeOnRequest;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}

	public String getGroupByRooms() {
		return groupByRooms;
	}

	public void setGroupByRooms(String groupByRooms) {
		this.groupByRooms = groupByRooms;
	}

	public IwtxRoomDTO[] getRoom() {
		return room;
	}

	public void setRoom(IwtxRoomDTO[] room) {
		this.room = room;
	}

	public List<SearchRoomDTO> getSearchRoomDTOs() {
		return searchRoomDTOs;
	}

	public void setSearchRoomDTOs(List<SearchRoomDTO> searchRoomDTOs) {
		this.searchRoomDTOs = searchRoomDTOs;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(int bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getSubResNo() {
		return subResNo;
	}

	public void setSubResNo(String subResNo) {
		this.subResNo = subResNo;
	}

	public String getAgencyRef() {
		return agencyRef;
	}

	public void setAgencyRef(String agencyRef) {
		this.agencyRef = agencyRef;
	}

	public int getBookingDateFrom() {
		return bookingDateFrom;
	}

	public void setBookingDateFrom(int bookingDateFrom) {
		this.bookingDateFrom = bookingDateFrom;
	}

	public int getBookingDateTo() {
		return bookingDateTo;
	}

	public void setBookingDateTo(int bookingDateTo) {
		this.bookingDateTo = bookingDateTo;
	}

	public String getDepartureDateFrom() {
		return departureDateFrom;
	}

	public void setDepartureDateFrom(String departureDateFrom) {
		this.departureDateFrom = departureDateFrom;
	}

	public String getDepartureDateTo() {
		return departureDateTo;
	}

	public void setDepartureDateTo(String departureDateTo) {
		this.departureDateTo = departureDateTo;
	}

	public String getArrivalDateFrom() {
		return arrivalDateFrom;
	}

	public void setArrivalDateFrom(String arrivalDateFrom) {
		this.arrivalDateFrom = arrivalDateFrom;
	}

	public String getArrivalDateTo() {
		return arrivalDateTo;
	}

	public void setArrivalDateTo(String arrivalDateTo) {
		this.arrivalDateTo = arrivalDateTo;
	}

	public String getStayDateFrom() {
		return stayDateFrom;
	}

	public void setStayDateFrom(String stayDateFrom) {
		this.stayDateFrom = stayDateFrom;
	}

	public String getStayDateTo() {
		return stayDateTo;
	}

	public void setStayDateTo(String stayDateTo) {
		this.stayDateTo = stayDateTo;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	@Override
	public String toString() {
		return "IwtxDTO [startDate=" + startDate + ", endDate=" + endDate + ", hotelCode=" + hotelCode + ", hotelCodes="
				+ hotelCodes + ", nationality=" + nationality + ", includeRateDetails=" + includeRateDetails
				+ ", includeOnRequest=" + includeOnRequest + ", cancellationPolicy=" + cancellationPolicy
				+ ", groupByRooms=" + groupByRooms + ", room=" + Arrays.toString(room) + ", source=" + source
				+ ", bookingNumber=" + bookingNumber + ", subResNo=" + subResNo + ", agencyRef=" + agencyRef
				+ ", bookingDateFrom=" + bookingDateFrom + ", bookingDateTo=" + bookingDateTo + ", departureDateFrom="
				+ departureDateFrom + ", departureDateTo=" + departureDateTo + ", arrivalDateFrom=" + arrivalDateFrom
				+ ", arrivalDateTo=" + arrivalDateTo + ", stayDateFrom=" + stayDateFrom + ", stayDateTo=" + stayDateTo
				+ ", agentId=" + agentId + "]";
	}
    
    
    
}
