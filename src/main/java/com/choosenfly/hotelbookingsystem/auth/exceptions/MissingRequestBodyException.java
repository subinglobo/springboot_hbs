package com.choosenfly.hotelbookingsystem.auth.exceptions;

public class MissingRequestBodyException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingRequestBodyException(String message) {
        super(message);
    }
}