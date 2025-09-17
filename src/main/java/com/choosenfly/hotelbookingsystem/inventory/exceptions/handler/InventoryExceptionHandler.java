package com.choosenfly.hotelbookingsystem.inventory.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.common.error.dto.ErrorResponse;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.InvalidDataException;
import com.choosenfly.hotelbookingsystem.inventory.exceptions.SpecialRatePersistException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

@RestControllerAdvice(basePackages = "com.choosenfly.hotelbookingsystem.inventory")
public class InventoryExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
	    String errorMessage = ex.getBindingResult().getFieldErrors().stream().findFirst()
	            .map(fieldError -> fieldError.getField().toUpperCase() + " - " + fieldError.getDefaultMessage())
	            .orElse("Validation error");

	    // Throw your own exception
	    throw new CustomValidationException(errorMessage);
	}
	
	@ExceptionHandler(CustomValidationException.class)
	public ResponseEntity<String> handleCustomValidationException(CustomValidationException ex) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(MissingRequestBodyException.class)
	public ResponseEntity<ErrorResponse> handleMissingRequestBodyException(MissingRequestBodyException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorResponse> handleInvaildDataException(InvalidDataException ex){
		
		String message = ex.getMessage();
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", message);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(SpecialRatePersistException.class)
	public ResponseEntity<ErrorResponse> handleSpecialRatePersistException(SpecialRatePersistException ex){
		
		String message = ex.getMessage();
		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", message);
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex) {
	    String message = "Invalid input format for request body";

	    // Optional: try to extract the root cause (Jackson parsing error message)
	    
	    Throwable cause = ex.getMostSpecificCause();
	    if(cause instanceof MismatchedInputException inputException) {
	    	if(!inputException.getPath().get(0).getFieldName().isEmpty()) {
	    		String fieldName = inputException.getPath().get(0).getFieldName();
	    		 message = "Invalid input for field '" + fieldName + "'";
	    	}
	    }

	    ErrorResponse errorResponse = new ErrorResponse(
	            HttpStatus.BAD_REQUEST.value(),
	            "Bad Request",
	            message
	    );

	    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
