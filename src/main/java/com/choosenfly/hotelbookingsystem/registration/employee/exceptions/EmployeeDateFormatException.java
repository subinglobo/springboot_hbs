package com.choosenfly.hotelbookingsystem.registration.employee.exceptions;

public class EmployeeDateFormatException extends RuntimeException {
    
    public EmployeeDateFormatException(String message) {
        super(message);
    }
    
    public EmployeeDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
