package com.choosenfly.hotelbookingsystem.auth.exceptions;

public class MissingEmailException extends UserRegistrationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingEmailException(String message) {
		super(message);
	}
}