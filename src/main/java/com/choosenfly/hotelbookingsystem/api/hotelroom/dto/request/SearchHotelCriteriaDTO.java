package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request;

import java.util.Date;
import java.util.List;

/**
 * Request DTO for hotel room search criteria
 */
public class SearchHotelCriteriaDTO {
    private Date checkInDate;
    private Date checkOutDate;
    private String hotelCode; // Format like "IN108" where 108 is hotelId
    private String nationality; // e.g., "IN"
    private String agentId;
    private int apiId; // If 1, trigger in-house search
    private List<SearchRoomDTO> rooms; // Each with adults, children, adultAges, childAges
    private String nativeCountryId;
    private String marketId;

    // Constructors
    public SearchHotelCriteriaDTO() {}

    public SearchHotelCriteriaDTO(Date checkInDate, Date checkOutDate, String hotelCode, String nationality, 
                                 String agentId, int apiId, List<SearchRoomDTO> rooms) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotelCode = hotelCode;
        this.nationality = nationality;
        this.agentId = agentId;
        this.apiId = apiId;
        this.rooms = rooms;
    }

    // Getters and Setters
    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public List<SearchRoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<SearchRoomDTO> rooms) {
        this.rooms = rooms;
    }

    public String getNativeCountryId() {
        return nativeCountryId;
    }

    public void setNativeCountryId(String nativeCountryId) {
        this.nativeCountryId = nativeCountryId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
}
