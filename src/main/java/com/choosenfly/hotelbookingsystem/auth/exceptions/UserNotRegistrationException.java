package com.choosenfly.hotelbookingsystem.auth.exceptions;

public class UserNotRegistrationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotRegistrationException(String message) {
		super(message);
	}
}