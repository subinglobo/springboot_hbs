package com.choosenfly.hotelbookingsystem.auth.exceptions;

public class RegisteredUserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisteredUserNotFoundException(String message) {
		super(message);
	}
}