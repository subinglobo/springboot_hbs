package com.choosenfly.hotelbookingsystem.api.x3.exception;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.common.HotelRoomSearchErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


/**
 * Global exception handler for X3 API related exceptions
 */
@RestControllerAdvice
@org.springframework.core.annotation.Order(1)
public class X3ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(X3ExceptionHandler.class);

    /**
     * Handle X3 No Availability Exception
     */
    @ExceptionHandler(X3NoAvailabilityException.class)
    public ResponseEntity<HotelRoomSearchErrorResponse> handleX3NoAvailabilityException(
            X3NoAvailabilityException ex, WebRequest request) {
        
        logger.warn("X3 No Availability Exception: {}", ex.getMessage());
        
        HotelRoomSearchErrorResponse errorResponse = new HotelRoomSearchErrorResponse(
                ex.getErrorCode(),
                "No availability found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<HotelRoomSearchErrorResponse>(errorResponse, HttpStatus.valueOf(ex.getHttpStatus()));
    }

    /**
     * Handle X3 Configuration Exception
     */
    @ExceptionHandler(X3ConfigurationException.class)
    public ResponseEntity<HotelRoomSearchErrorResponse> handleX3ConfigurationException(
            X3ConfigurationException ex, WebRequest request) {
        
        logger.error("X3 Configuration Error: {}", ex.getMessage(), ex);
        
        HotelRoomSearchErrorResponse errorResponse = new HotelRoomSearchErrorResponse(
                ex.getErrorCode(),
                "Service configuration error",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<HotelRoomSearchErrorResponse>(errorResponse, HttpStatus.valueOf(ex.getHttpStatus()));
    }

    /**
     * Handle general X3 API Exception
     */
    @ExceptionHandler(X3ApiException.class)
    public ResponseEntity<HotelRoomSearchErrorResponse> handleX3ApiException(
            X3ApiException ex, WebRequest request) {
        
        logger.error("X3 API Error: {}", ex.getMessage(), ex);
        
        // Extract user-friendly message from the technical error
        String userFriendlyMessage = extractUserFriendlyMessage(ex);
        
        HotelRoomSearchErrorResponse errorResponse = new HotelRoomSearchErrorResponse(
                ex.getErrorCode(),
                userFriendlyMessage,
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<HotelRoomSearchErrorResponse>(errorResponse, HttpStatus.valueOf(ex.getHttpStatus()));
    }

    /**
     * Handle RuntimeExceptions that are X3-related
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HotelRoomSearchErrorResponse> handleX3RuntimeException(
            RuntimeException ex, WebRequest request) {
        
        // Only handle if this is related to X3 operations based on message content
        String message = ex.getMessage();
        if (message == null || (!message.contains("X3") && !message.contains("x3"))) {
            throw ex; // Re-throw to let other handlers handle it
        }
        
        logger.error("Unexpected error in X3 operation: {}", ex.getMessage(), ex);
        
        // Extract the root cause message for better UI display
        String userFriendlyMessage = extractUserFriendlyMessage(ex);
        
        HotelRoomSearchErrorResponse errorResponse = new HotelRoomSearchErrorResponse(
                "X3_INTERNAL_ERROR",
                userFriendlyMessage,
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Extract user-friendly error message from exception chain
     */
    private String extractUserFriendlyMessage(Exception ex) {
        String message = ex.getMessage();
        
        if (message == null) {
            return "Service temporarily unavailable";
        }
        
        // Look for specific error patterns and return simple user-friendly messages
        if (message.contains("No Availability Found") || message.contains("No availability found")) {
            return "No availability found";
        }
        
        if (message.contains("Configuration")) {
            return "Service configuration error";
        }
        
        if (message.contains("timeout") || message.contains("Timeout")) {
            return "Request timeout";
        }
        
        if (message.contains("connection") || message.contains("Connection")) {
            return "Connection error";
        }
        
        // For any other technical errors, return a generic message
        return "Service temporarily unavailable";
    }
}
