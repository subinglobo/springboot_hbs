package com.choosenfly.hotelbookingsystem.registration.employee.exceptions;

public class EmployeeValidationException extends RuntimeException {
    
    public EmployeeValidationException(String message) {
        super(message);
    }
    
    public EmployeeValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
