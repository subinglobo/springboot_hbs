package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response;

import java.util.List;

/**
 * DTO representing a hotel in the search response
 */
public class HotelResponse {

    private String hotelId;
    private String hotelName;
    private Integer starRating;
    private String propertyType;
    private String chain;
    private String city;
    private String timeZone;
    private String hotelAddress;
    private String hotelPhoneNumber;
    private GeoLocation geoLocation;
    private List<RoomCategoryResponse> roomCategories;
    
    // Booking details
    private String checkInDate;
    private String checkOutDate;
    private String nationality;
    private Integer numberOfRooms;
    private Integer numberOfGuests;
    private String guestBreakdown;
    private String destination;

    // Constructors
    public HotelResponse() {}

    public HotelResponse(String hotelId, String hotelName, Integer starRating, String propertyType,
                        String chain, String city, String timeZone, GeoLocation geoLocation,
                        List<RoomCategoryResponse> roomCategories) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.starRating = starRating;
        this.propertyType = propertyType;
        this.chain = chain;
        this.city = city;
        this.timeZone = timeZone;
        this.geoLocation = geoLocation;
        this.roomCategories = roomCategories;
    }

    // Getters and Setters
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public List<RoomCategoryResponse> getRoomCategories() {
        return roomCategories;
    }

    public void setRoomCategories(List<RoomCategoryResponse> roomCategories) {
        this.roomCategories = roomCategories;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public void setHotelPhoneNumber(String hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getGuestBreakdown() {
        return guestBreakdown;
    }

    public void setGuestBreakdown(String guestBreakdown) {
        this.guestBreakdown = guestBreakdown;
    }

    @Override
    public String toString() {
        return "HotelResponse{" +
                "hotelId='" + hotelId + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", starRating=" + starRating +
                ", propertyType='" + propertyType + '\'' +
                ", chain='" + chain + '\'' +
                ", city='" + city + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", geoLocation=" + geoLocation +
                ", roomCategories=" + roomCategories +
                '}';
    }
}
