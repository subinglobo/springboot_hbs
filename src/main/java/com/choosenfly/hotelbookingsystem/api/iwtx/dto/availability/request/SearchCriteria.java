package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request;

import jakarta.xml.bind.annotation.XmlElement;

public class SearchCriteria {
    
    private RoomConfiguration roomConfiguration;
    private String startDate;
    private String endDate;
    private String hotelCode;
    private String nationality;
    private String includeRateDetails;
    private String cancellationPolicy;
    private String groupByRooms;
    
    @XmlElement(name = "RoomConfiguration")
    public RoomConfiguration getRoomConfiguration() {
        return roomConfiguration;
    }
    
    public void setRoomConfiguration(RoomConfiguration roomConfiguration) {
        this.roomConfiguration = roomConfiguration;
    }
    
    @XmlElement(name = "StartDate")
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    @XmlElement(name = "EndDate")
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    @XmlElement(name = "HotelCode")
    public String getHotelCode() {
        return hotelCode;
    }
    
    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
    
    @XmlElement(name = "Nationality")
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    @XmlElement(name = "IncludeRateDetails")
    public String getIncludeRateDetails() {
        return includeRateDetails;
    }
    
    public void setIncludeRateDetails(String includeRateDetails) {
        this.includeRateDetails = includeRateDetails;
    }
    
    @XmlElement(name = "CancellationPolicy")
    public String getCancellationPolicy() {
        return cancellationPolicy;
    }
    
    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }
    
    @XmlElement(name = "GroupByRooms")
    public String getGroupByRooms() {
        return groupByRooms;
    }
    
    public void setGroupByRooms(String groupByRooms) {
        this.groupByRooms = groupByRooms;
    }
}
