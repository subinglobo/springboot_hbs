package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response;

import java.util.List;

/**
 * Response DTO for hotel room search API
 */
public class HotelRoomSearchResponse {

    private List<HotelResponse> hotels;
    private String message;
    private boolean success;

    // Constructors
    public HotelRoomSearchResponse() {}

    public HotelRoomSearchResponse(List<HotelResponse> hotels, String message, boolean success) {
        this.hotels = hotels;
        this.message = message;
        this.success = success;
    }

    // Static factory methods
    public static HotelRoomSearchResponse success(List<HotelResponse> hotels) {
        return new HotelRoomSearchResponse(hotels, "Search completed successfully", true);
    }

    public static HotelRoomSearchResponse error(String message) {
        return new HotelRoomSearchResponse(null, message, false);
    }

    // Getters and Setters
    public List<HotelResponse> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelResponse> hotels) {
        this.hotels = hotels;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "HotelRoomSearchResponse{" +
                "hotels=" + hotels +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
