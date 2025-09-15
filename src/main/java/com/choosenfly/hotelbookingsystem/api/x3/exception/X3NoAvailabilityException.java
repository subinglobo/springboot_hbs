package com.choosenfly.hotelbookingsystem.api.x3.exception;

/**
 * Exception thrown when no hotel availability is found in X3 API
 */
public class X3NoAvailabilityException extends X3ApiException {
    
    public X3NoAvailabilityException(String message) {
        super(message, "X3_NO_AVAILABILITY", 404);
    }
    
    public X3NoAvailabilityException(String hotelCode, String checkIn, String checkOut) {
        super(String.format("No availability found for hotel %s from %s to %s", hotelCode, checkIn, checkOut), 
              "X3_NO_AVAILABILITY", 404);
    }
}
