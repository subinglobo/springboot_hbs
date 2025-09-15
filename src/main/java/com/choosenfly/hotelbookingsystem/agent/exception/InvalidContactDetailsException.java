package com.choosenfly.hotelbookingsystem.agent.exception;

public class InvalidContactDetailsException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidContactDetailsException(String message) {
        super(message);
    }
}