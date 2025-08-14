package com.choosenfly.hotelbookingsystem.auth.exceptions.handler;



import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.choosenfly.hotelbookingsystem.auth.exceptions.EmailSendException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.InvalidRoleException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingCredentialsException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingEmailException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.OTPException;
import com.choosenfly.hotelbookingsystem.auth.exceptions.UserRegistrationException;
import com.choosenfly.hotelbookingsystem.common.error.dto.ErrorResponse;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.InvalidUserTypeException;

@RestControllerAdvice(basePackages = "com.choosenfly.hotelbookingsystem.auth")
public class AuthExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AuthExceptionHandler.class);

    @ExceptionHandler(MissingEmailException.class)
    public ResponseEntity<ErrorResponse> handleMissingEmail(MissingEmailException ex) {
        log.warn("Missing email: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MissingCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleMissingCredentials(MissingCredentialsException ex) {
        log.warn("Missing credentials: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleUserRegistration(UserRegistrationException ex) {
        log.warn("User registration error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRole(InvalidRoleException ex) {
        log.warn("Invalid role: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
        log.error("Unexpected error in authentication module", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong. Please contact support.");
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors()
                           .stream()
                           .map(error -> error.getField() + ": " + error.getDefaultMessage())
                           .collect(Collectors.joining(", "));
        log.warn("Validation error: {}", message);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, message);
    }
    
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.warn("Request body error: {}", ex.getMessage());
        if (ex.getMessage().contains("Required request body is missing")) {
            return handleMissingRequestBody(new MissingRequestBodyException("Request body is missing or malformed"));
        }
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid request body format");
    }

    @ExceptionHandler(MissingRequestBodyException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestBody(MissingRequestBodyException ex) {
        log.warn("Missing request body: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    
    @ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleHotelNotFoundException(HotelNotFoundException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Hotel Not Found", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
    
    @ExceptionHandler(InvalidUserTypeException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUserTypeException(InvalidUserTypeException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse response = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return new ResponseEntity<>(response, status);
    }
    
    @ExceptionHandler(OTPException.class)
    public ResponseEntity<ErrorResponse> handleOTPException(OTPException ex) {
    	
    	String errorMessage = ex.getMessage();
    	
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "OTP Exception", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
   
    }
    
    @ExceptionHandler(EmailSendException.class)
   	public ResponseEntity<ErrorResponse> handleEmailSendException(EmailSendException ex) {
   		// Extract the first error message
   		String errorMessage = ex.getMessage();
   		
   		
   		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server error", errorMessage);
   		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
   	}

}
