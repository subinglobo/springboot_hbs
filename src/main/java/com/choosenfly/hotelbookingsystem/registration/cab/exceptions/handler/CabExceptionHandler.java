package com.choosenfly.hotelbookingsystem.registration.cab.exceptions.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.choosenfly.hotelbookingsystem.common.error.dto.ErrorResponse;
import com.choosenfly.hotelbookingsystem.registration.cab.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.registration.cab.exceptions.InvalidCabLocationException;

@RestControllerAdvice(basePackages = {
	    "com.choosenfly.hotelbookingsystem.registration.cab"
	})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CabExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFound(EntityNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidCabLocationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCabLocation(InvalidCabLocationException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Cab Location",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Optional: fallback for other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
