package com.choosenfly.hotelbookingsystem.registration.employee.exceptions.handler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.choosenfly.hotelbookingsystem.agent.exception.AgentRegistrationException;
import com.choosenfly.hotelbookingsystem.agent.exception.handler.AgentExceptionHandler;
import com.choosenfly.hotelbookingsystem.common.error.dto.ErrorResponse;
import com.choosenfly.hotelbookingsystem.registration.employee.exceptions.EmployeetRegistrationException;
import com.choosenfly.hotelbookingsystem.registration.employee.exceptions.EmployeeDateFormatException;
import com.choosenfly.hotelbookingsystem.registration.employee.exceptions.EmployeeValidationException;

@RestControllerAdvice(basePackages = "com.choosenfly.hotelbookingsystem.registration.employee")
public class EmployeeExceptionsHandler {
	
    private static final Logger log = LoggerFactory.getLogger(EmployeeExceptionsHandler.class);
	
    @ExceptionHandler(EmployeetRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleRegistration(EmployeetRegistrationException ex) {
        log.warn("Employee registration error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        log.warn("Employee registration error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse response = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return new ResponseEntity<>(response, status);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.warn("Validation error occurred: {}", ex.getMessage());
        
        // Handle date conversion errors specifically
        String errorMsg = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> {
                    if ("dob".equals(err.getField()) && err.getCode().equals("typeMismatch")) {
                        return "Date of birth must be in format dd/MM/yyyy (e.g., 25/12/2000)";
                    }
                    return err.getField() + ": " + err.getDefaultMessage();
                })
                .collect(Collectors.joining(", "));

        if (errorMsg.isEmpty()) {
            errorMsg = "Invalid input data provided";
        }

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                errorMsg
        );

        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(TypeMismatchException ex) {
        log.warn("Type mismatch error: {}", ex.getMessage());
        
        String errorMsg = "Invalid data format";
        if (ex.getPropertyName() != null && ex.getPropertyName().equals("dob")) {
            errorMsg = "Date of birth must be in format dd/MM/yyyy (e.g., 25/12/2000). Received: " + ex.getValue();
        }
        
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMsg);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.warn("Message not readable error: {}", ex.getMessage());
        
        String errorMsg = "Invalid request format";
        if (ex.getMessage().contains("Date")) {
            errorMsg = "Invalid date format. Please use dd/MM/yyyy format for dates (e.g., 25/12/2000)";
        }
        
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMsg);
    }
    
    @ExceptionHandler(EmployeeDateFormatException.class)
    public ResponseEntity<ErrorResponse> handleDateFormatError(EmployeeDateFormatException ex) {
        log.warn("Employee date format error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    
    @ExceptionHandler(EmployeeValidationException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeValidation(EmployeeValidationException ex) {
        log.warn("Employee validation error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error in employee module: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred. Please try again.");
    }

}
