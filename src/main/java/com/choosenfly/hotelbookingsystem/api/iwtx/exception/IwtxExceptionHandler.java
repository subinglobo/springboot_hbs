package com.choosenfly.hotelbookingsystem.api.iwtx.exception;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.common.HotelRoomSearchErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for IWTX API related exceptions
 */
@RestControllerAdvice
@org.springframework.core.annotation.Order(2)
public class IwtxExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(IwtxExceptionHandler.class);

    /**
     * Handle IWTX No Availability Exception
     */
    @ExceptionHandler(IwtxNoAvailabilityException.class)
    public ResponseEntity<HotelRoomSearchErrorResponse> handleIwtxNoAvailabilityException(
            IwtxNoAvailabilityException ex, WebRequest request) {
        
        logger.warn("IWTX No Availability: {}", ex.getMessage());
        
        HotelRoomSearchErrorResponse errorResponse = new HotelRoomSearchErrorResponse(
            ex.getErrorCode(),
            ex.getMessage(),
            "No rooms available for the specified search criteria",
            request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle IWTX Configuration Exception
     */
    @ExceptionHandler(IwtxConfigurationException.class)
    public ResponseEntity<HotelRoomSearchErrorResponse> handleIwtxConfigurationException(
            IwtxConfigurationException ex, WebRequest request) {
        
        logger.error("IWTX Configuration Error: {}", ex.getMessage(), ex);
        
        HotelRoomSearchErrorResponse errorResponse = new HotelRoomSearchErrorResponse(
            ex.getErrorCode(),
            "IWTX API configuration error",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle general IWTX API Exception
     */
    @ExceptionHandler(IwtxApiException.class)
    public ResponseEntity<HotelRoomSearchErrorResponse> handleIwtxApiException(
            IwtxApiException ex, WebRequest request) {
        
        logger.error("IWTX API Error: {}", ex.getMessage(), ex);
        
        HotelRoomSearchErrorResponse errorResponse = new HotelRoomSearchErrorResponse(
            ex.getErrorCode(),
            ex.getMessage(),
            "An error occurred while processing the IWTX API request",
            request.getDescription(false).replace("uri=", "")
        );
        
        HttpStatus status = HttpStatus.valueOf(ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Handle RuntimeExceptions that are IWTX-related
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HotelRoomSearchErrorResponse> handleIwtxRuntimeException(
            RuntimeException ex, WebRequest request) {
        
        // Only handle if this is related to IWTX operations based on message content
        String message = ex.getMessage();
        if (message == null || (!message.contains("IWTX") && !message.contains("iwtx"))) {
            throw ex; // Re-throw to let other handlers handle it
        }
        
        logger.error("Unexpected error in IWTX context: {}", ex.getMessage(), ex);
        
        // Extract user-friendly message
        String userFriendlyMessage = extractUserFriendlyMessage(ex);
        
        HotelRoomSearchErrorResponse errorResponse = new HotelRoomSearchErrorResponse(
            "IWTX_INTERNAL_ERROR",
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
            return "An unexpected error occurred";
        }
        
        // Look for specific error patterns and extract the meaningful part
        if (message.contains("No Availability Found")) {
            return "No rooms available for the selected dates";
        }
        
        if (message.contains("Configuration")) {
            return "Service configuration error";
        }
        
        // Remove technical error prefixes
        String[] prefixes = {
            "IWTX API call failed: ",
            "Failed to call IWTX API: ",
            "Failed to parse IWTX API response: ",
            "RuntimeException: "
        };
        
        for (String prefix : prefixes) {
            if (message.startsWith(prefix)) {
                message = message.substring(prefix.length());
            }
        }
        
        return message;
    }
}
