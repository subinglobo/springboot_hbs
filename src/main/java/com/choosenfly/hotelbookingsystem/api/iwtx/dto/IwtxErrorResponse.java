package com.choosenfly.hotelbookingsystem.api.iwtx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Standard error response DTO for IWTX API
 */
public class IwtxErrorResponse {
    
    @JsonProperty("success")
    private boolean success = false;
    
    @JsonProperty("error")
    private ErrorDetails error;
    
    @JsonProperty("timestamp")
    private String timestamp;
    
    @JsonProperty("path")
    private String path;
    
    public IwtxErrorResponse() {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
    public IwtxErrorResponse(String code, String message, String path) {
        this();
        this.error = new ErrorDetails(code, message);
        this.path = path;
    }
    
    public IwtxErrorResponse(String code, String message, String details, String path) {
        this();
        this.error = new ErrorDetails(code, message, details);
        this.path = path;
    }
    
    // Getters and Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public ErrorDetails getError() { return error; }
    public void setError(ErrorDetails error) { this.error = error; }
    
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    
    /**
     * Error details nested class
     */
    public static class ErrorDetails {
        @JsonProperty("code")
        private String code;
        
        @JsonProperty("message")
        private String message;
        
        @JsonProperty("details")
        private String details;
        
        public ErrorDetails() {}
        
        public ErrorDetails(String code, String message) {
            this.code = code;
            this.message = message;
        }
        
        public ErrorDetails(String code, String message, String details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }
        
        // Getters and Setters
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
    }
}
