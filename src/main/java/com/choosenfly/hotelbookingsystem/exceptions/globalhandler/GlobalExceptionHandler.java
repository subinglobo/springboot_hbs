package com.choosenfly.hotelbookingsystem.exceptions.globalhandler;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.choosenfly.hotelbookingsystem.dto.error.ErrorResponse;
import com.choosenfly.hotelbookingsystem.exceptions.DataNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.InvalidFeildException;
import com.choosenfly.hotelbookingsystem.exceptions.InvalidUserTypeException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.exceptions.StateCountryMismatchException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Pattern DUPLICATE_KEY_PATTERN = Pattern.compile("Key \\((.*?)\\)=\\((.*?)\\)");

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
		// Extract the first error message
		String errorMessage = ex.getBindingResult().getFieldErrors().stream().findFirst()
				.map(fieldError -> fieldError.getField().toUpperCase() + " - " + fieldError.getDefaultMessage()) // Include
																													// field
																													// name
				.orElse("Validation error");

		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleHotelNotFoundException(HotelNotFoundException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Hotel Not Found", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidUserTypeException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUserTypeException(InvalidUserTypeException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

		// Default response
		String userMessage = "A duplicate entry was detected.";
		HttpStatus status = HttpStatus.CONFLICT; // 409 for duplicate resource

		// Extract the cause
		Throwable cause = ex.getMostSpecificCause();
		if (cause instanceof SQLException) {
			String sqlMessage = cause.getMessage();
			if (sqlMessage != null) {
				// Parse duplicate key details (PostgreSQL-specific example)
				Matcher matcher = DUPLICATE_KEY_PATTERN.matcher(sqlMessage);
				if (matcher.find()) {
					String fieldName = matcher.group(1); // e.g., "username"
					String value = matcher.group(2); // e.g., "test"
					userMessage = String.format("%s '%s' is already taken.", capitalize(fieldName), value);
				} else if (sqlMessage.contains("duplicate key")) {
					userMessage = "A duplicate value already exists.";
				}
			}
		}

		// Build the error response
		ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), userMessage);

		return new ResponseEntity<>(errorResponse, status);
	}

	// Utility to capitalize field names
	private String capitalize(String str) {
		if (str == null || str.isEmpty())
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	@ExceptionHandler(MissingRequestBodyException.class)
	public ResponseEntity<ErrorResponse> handleMissingRequestBodyException(MissingRequestBodyException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidFeildException.class)
	public ResponseEntity<ErrorResponse> handleInvalidFeildException(InvalidFeildException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityCreationException.class)
	public ResponseEntity<ErrorResponse> handleEntityCreationException(EntityCreationException ex) {
	    String errorMessage = ex.getMessage();

	    ErrorResponse errorResponse = new ErrorResponse(
	            HttpStatus.INTERNAL_SERVER_ERROR.value(),
	            "Entity Creation Failed",
	            errorMessage
	    );

	    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(StateCountryMismatchException.class)
	public ResponseEntity<ErrorResponse> handleStateCountryMismatch(StateCountryMismatchException ex) {
		// Extract the first error message
		String errorMessage = ex.getMessage();
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

} 