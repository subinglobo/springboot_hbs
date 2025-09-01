package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;

public class Hotel {
    
    private String sourceId;
    private String hotelId;
    private String hotelName;
    private String propertyType;
    private String starRating;
    private GeoLocation geoLocation;
    private String chain;
    private String hotelCode;
    private String timeZone;
    private String city;
    private RoomTypeDetails roomTypeDetails;
    private String startDate;
    private String endDate;
    
    @XmlElement(name = "SourceId")
    public String getSourceId() {
        return sourceId;
    }
    
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
    
    @XmlElement(name = "HotelId")
    public String getHotelId() {
        return hotelId;
    }
    
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
    
    @XmlElement(name = "HotelName")
    public String getHotelName() {
        return hotelName;
    }
    
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    
    @XmlElement(name = "PropertyType")
    public String getPropertyType() {
        return propertyType;
    }
    
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    
    @XmlElement(name = "StarRating")
    public String getStarRating() {
        return starRating;
    }
    
    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }
    
    @XmlElement(name = "GeoLocation")
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }
    
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }
    
    @XmlElement(name = "Chain")
    public String getChain() {
        return chain;
    }
    
    public void setChain(String chain) {
        this.chain = chain;
    }
    
    @XmlElement(name = "HotelCode")
    public String getHotelCode() {
        return hotelCode;
    }
    
    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
    
    @XmlElement(name = "TimeZone")
    public String getTimeZone() {
        return timeZone;
    }
    
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    
    @XmlElement(name = "City")
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @XmlElement(name = "RoomTypeDetails")
    public RoomTypeDetails getRoomTypeDetails() {
        return roomTypeDetails;
    }
    
    public void setRoomTypeDetails(RoomTypeDetails roomTypeDetails) {
        this.roomTypeDetails = roomTypeDetails;
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
}
