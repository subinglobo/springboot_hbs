package com.choosenfly.hotelbookingsystem.inventory.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.choosenfly.hotelbookingsystem.auth.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.common.error.dto.ErrorResponse;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;

@RestControllerAdvice(basePackages = "com.choosenfly.hotelbookingsystem.inventory")
public class InventoryExceptionHandler {

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
}
