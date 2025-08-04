package com.choosenfly.hotelbookingsystem.auth.exceptions;

public class MissingCredentialsException extends UserRegistrationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingCredentialsException(String message) {
        super(message);
    }
}