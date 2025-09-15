package com.choosenfly.hotelbookingsystem.api.iwtx.exception;

/**
 * Exception thrown when no hotel availability is found in IWTX API
 */
public class IwtxNoAvailabilityException extends IwtxApiException {
    
    public IwtxNoAvailabilityException(String message) {
        super(message, "IWTX_NO_AVAILABILITY", 404);
    }
    
    public IwtxNoAvailabilityException(String hotelCode, String checkIn, String checkOut) {
        super(String.format("No availability found for hotel %s from %s to %s", hotelCode, checkIn, checkOut), 
              "IWTX_NO_AVAILABILITY", 404);
    }
}
