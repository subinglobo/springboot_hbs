package com.choosenfly.hotelbookingsystem.api.iwtx.exception;

/**
 * Exception thrown when IWTX API configuration is invalid or missing
 */
public class IwtxConfigurationException extends IwtxApiException {
    
    public IwtxConfigurationException(String message) {
        super(message, "IWTX_CONFIGURATION_ERROR", 500);
    }
    
    public IwtxConfigurationException(String message, Throwable cause) {
        super(message, "IWTX_CONFIGURATION_ERROR", cause);
    }
}
