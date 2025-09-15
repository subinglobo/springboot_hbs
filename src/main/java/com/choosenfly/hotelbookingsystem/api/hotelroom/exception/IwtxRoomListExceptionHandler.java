package com.choosenfly.hotelbookingsystem.api.hotelroom.exception;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception handler for IWTX room list API
 */
@RestControllerAdvice(basePackages = "com.choosenfly.hotelbookingsystem.api.hotelroom")
@org.springframework.core.annotation.Order(3)
public class IwtxRoomListExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(IwtxRoomListExceptionHandler.class);

    /**
     * Handle validation errors for hotel room search
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HotelRoomSearchResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String errorMessage = "Validation failed: " + errors.toString();
        logger.error("Hotel room search validation error: {}", errorMessage);
        
        return ResponseEntity.badRequest()
            .body(HotelRoomSearchResponse.error(errorMessage));
    }

    /**
     * Handle hotel room search specific exceptions
     */
    @ExceptionHandler(HotelRoomSearchException.class)
    public ResponseEntity<HotelRoomSearchResponse> handleHotelRoomSearchException(
            HotelRoomSearchException ex) {
        
        logger.error("Hotel room search error [{}]: {}", ex.getErrorCode(), ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(HotelRoomSearchResponse.error(ex.getMessage()));
    }

    /**
     * Handle illegal argument exceptions for hotel room search
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HotelRoomSearchResponse> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        
        logger.error("Invalid hotel room search argument: {}", ex.getMessage());
        
        return ResponseEntity.badRequest()
            .body(HotelRoomSearchResponse.error("Invalid request: " + ex.getMessage()));
    }

    /**
     * Handle runtime exceptions that are not provider-specific
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HotelRoomSearchResponse> handleRuntimeException(RuntimeException ex) {
        // Skip provider-specific exceptions - let them be handled by their respective handlers
        String message = ex.getMessage();
        if (message != null && (message.contains("X3") || message.contains("IWTX"))) {
            throw ex; // Re-throw to let provider-specific handlers catch it
        }
        
        logger.error("Runtime error in hotel room search", ex);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(HotelRoomSearchResponse.error("An unexpected error occurred. Please try again later."));
    }
}
