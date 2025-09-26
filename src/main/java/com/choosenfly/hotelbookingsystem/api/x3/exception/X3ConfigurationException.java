package com.choosenfly.hotelbookingsystem.api.x3.exception;

/**
 * Exception thrown when X3 API configuration is invalid or missing
 */
public class X3ConfigurationException extends X3ApiException {
    
    public X3ConfigurationException(String message) {
        super(message, "X3_CONFIGURATION_ERROR", 500);
    }
    
    public X3ConfigurationException(String message, Throwable cause) {
        super(message, "X3_CONFIGURATION_ERROR", cause);
    }
}
