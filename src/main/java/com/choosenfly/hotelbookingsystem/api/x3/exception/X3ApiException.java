package com.choosenfly.hotelbookingsystem.api.x3.exception;

/**
 * Custom exception for X3 API related errors
 */
public class X3ApiException extends RuntimeException {
    
    private final String errorCode;
    private final int httpStatus;
    
    public X3ApiException(String message) {
        super(message);
        this.errorCode = "X3_API_ERROR";
        this.httpStatus = 500;
    }
    
    public X3ApiException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = 500;
    }
    
    public X3ApiException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
    
    public X3ApiException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "X3_API_ERROR";
        this.httpStatus = 500;
    }
    
    public X3ApiException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = 500;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public int getHttpStatus() {
        return httpStatus;
    }
}
