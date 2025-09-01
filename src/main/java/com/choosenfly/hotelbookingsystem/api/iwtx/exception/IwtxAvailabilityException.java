package com.choosenfly.hotelbookingsystem.api.iwtx.exception;

public class IwtxAvailabilityException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private String errorCode;
    private String errorMessage;
    
    public IwtxAvailabilityException(String message) {
        super(message);
        this.errorMessage = message;
    }
    
    public IwtxAvailabilityException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public IwtxAvailabilityException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = message;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
