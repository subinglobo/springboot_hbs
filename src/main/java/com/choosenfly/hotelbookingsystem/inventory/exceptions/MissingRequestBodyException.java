package com.choosenfly.hotelbookingsystem.inventory.exceptions;

public class MissingRequestBodyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MissingRequestBodyException(String message) {
		super(message);
	}
}
