package com.choosenfly.hotelbookingsystem.inventory.exceptions.handler;

public class CustomValidationException extends RuntimeException {
    public CustomValidationException(String message) {
        super(message);
    }
}