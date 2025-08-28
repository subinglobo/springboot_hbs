package com.choosenfly.hotelbookingsystem.api.hotelroom.exception;

/**
 * Custom exception for hotel room search operations
 */
public class HotelRoomSearchException extends RuntimeException {

    private final String errorCode;

    public HotelRoomSearchException(String message) {
        super(message);
        this.errorCode = "HOTEL_SEARCH_ERROR";
    }

    public HotelRoomSearchException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public HotelRoomSearchException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "HOTEL_SEARCH_ERROR";
    }

    public HotelRoomSearchException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
