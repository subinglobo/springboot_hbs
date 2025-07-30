package com.choosenfly.hotelbookingsystem.exceptions;

public class MissingRequestBodyException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public MissingRequestBodyException(String message) {
		super(message);
	}
}