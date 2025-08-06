package com.choosenfly.hotelbookingsystem.agent.exception.handler;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.choosenfly.hotelbookingsystem.agent.exception.AgentRegistrationException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidContactDetailsException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidCountryException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidGSTDetailsException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidPlaceException;
import com.choosenfly.hotelbookingsystem.agent.exception.InvalidProvinceException;
import com.choosenfly.hotelbookingsystem.common.error.dto.ErrorResponse;

@RestControllerAdvice(basePackages = "com.choosenfly.hotelbookingsystem.agent")
public class AgentExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AgentExceptionHandler.class);

    @ExceptionHandler(AgentRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleRegistration(AgentRegistrationException ex) {
        log.warn("Agent registration error: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidCountryException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCountry(InvalidCountryException ex) {
        log.warn("Invalid country ID: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidProvinceException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProvince(InvalidProvinceException ex) {
        log.warn("Invalid province ID: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidPlaceException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPlace(InvalidPlaceException ex) {
        log.warn("Invalid place ID: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidContactDetailsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidContact(InvalidContactDetailsException ex) {
        log.warn("Invalid contact details: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidGSTDetailsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidGst(InvalidGSTDetailsException ex) {
        log.warn("Invalid GST details: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                          .getFieldErrors()
                          .stream()
                          .map(e -> e.getField() + ": " + e.getDefaultMessage())
                          .collect(Collectors.joining(", "));
        log.warn("Validation failed: {}", errors);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadable(HttpMessageNotReadableException ex) {
        log.warn("Malformed request body: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid or missing request body");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception in agent module", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please contact support.");
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse response = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return new ResponseEntity<>(response, status);
    }
}
