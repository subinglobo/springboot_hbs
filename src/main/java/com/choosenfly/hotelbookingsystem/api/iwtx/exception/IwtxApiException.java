package com.choosenfly.hotelbookingsystem.api.iwtx.exception;

/**
 * Custom exception for IWTX API related errors
 */
public class IwtxApiException extends RuntimeException {
    
    private final String errorCode;
    private final int httpStatus;
    
    public IwtxApiException(String message) {
        super(message);
        this.errorCode = "IWTX_API_ERROR";
        this.httpStatus = 500;
    }
    
    public IwtxApiException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = 500;
    }
    
    public IwtxApiException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
    
    public IwtxApiException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "IWTX_API_ERROR";
        this.httpStatus = 500;
    }
    
    public IwtxApiException(String message, String errorCode, Throwable cause) {
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
