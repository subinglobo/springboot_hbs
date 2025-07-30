package com.choosenfly.hotelbookingsystem.exceptions;

public class StateCountryMismatchException extends RuntimeException {
    public StateCountryMismatchException(String message) {
        super(message);
    }
    
    public StateCountryMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
