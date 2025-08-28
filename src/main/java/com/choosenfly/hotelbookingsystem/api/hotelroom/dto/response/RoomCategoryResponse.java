package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response;

import java.util.List;

/**
 * DTO representing a room category with multiple rate options
 */
public class RoomCategoryResponse {

    private String roomCategory;
    private String roomTypeCode;
    private String baseRoomType;
    private List<RateOptionResponse> availableRates;

    // Constructors
    public RoomCategoryResponse() {}

    public RoomCategoryResponse(String roomCategory, String roomTypeCode, String baseRoomType, 
                               List<RateOptionResponse> availableRates) {
        this.roomCategory = roomCategory;
        this.roomTypeCode = roomTypeCode;
        this.baseRoomType = baseRoomType;
        this.availableRates = availableRates;
    }

    // Getters and Setters
    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public String getBaseRoomType() {
        return baseRoomType;
    }

    public void setBaseRoomType(String baseRoomType) {
        this.baseRoomType = baseRoomType;
    }

    public List<RateOptionResponse> getAvailableRates() {
        return availableRates;
    }

    public void setAvailableRates(List<RateOptionResponse> availableRates) {
        this.availableRates = availableRates;
    }

    @Override
    public String toString() {
        return "RoomCategoryResponse{" +
                "roomCategory='" + roomCategory + '\'' +
                ", roomTypeCode='" + roomTypeCode + '\'' +
                ", baseRoomType='" + baseRoomType + '\'' +
                ", availableRates=" + availableRates +
                '}';
    }
}
