package com.choosenfly.hotelbookingsystem.api.iwtx.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateFormatService {
    
    private static final DateTimeFormatter IWTX_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter USER_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Convert user-friendly date format (YYYY-MM-DD) to IWTX format (YYYYMMDD)
     * 
     * @param userDate Date in YYYY-MM-DD format
     * @return Date in YYYYMMDD format for IWTX API
     */
    public String convertToIwtxFormat(String userDate) {
        if (userDate == null || userDate.trim().isEmpty()) {
            return userDate;
        }
        
        try {
            // If already in IWTX format (8 digits), return as is
            if (userDate.matches("\\d{8}")) {
                return userDate;
            }
            
            // Parse user format and convert to IWTX format
            LocalDate date = LocalDate.parse(userDate, USER_FORMAT);
            return date.format(IWTX_FORMAT);
            
        } catch (DateTimeParseException e) {
            // If parsing fails, return original string
            return userDate;
        }
    }
    
    /**
     * Convert IWTX date format (YYYYMMDD) to user-friendly format (YYYY-MM-DD)
     * 
     * @param iwtxDate Date in YYYYMMDD format
     * @return Date in YYYY-MM-DD format
     */
    public String convertToUserFormat(String iwtxDate) {
        if (iwtxDate == null || iwtxDate.trim().isEmpty()) {
            return iwtxDate;
        }
        
        try {
            // If already in user format (contains hyphens), return as is
            if (iwtxDate.contains("-")) {
                return iwtxDate;
            }
            
            // Parse IWTX format and convert to user format
            LocalDate date = LocalDate.parse(iwtxDate, IWTX_FORMAT);
            return date.format(USER_FORMAT);
            
        } catch (DateTimeParseException e) {
            // If parsing fails, return original string
            return iwtxDate;
        }
    }
    
    /**
     * Validate if date string is in valid format (either YYYY-MM-DD or YYYYMMDD)
     * 
     * @param dateStr Date string to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return false;
        }
        
        try {
            if (dateStr.contains("-")) {
                LocalDate.parse(dateStr, USER_FORMAT);
            } else {
                LocalDate.parse(dateStr, IWTX_FORMAT);
            }
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * Get current date in IWTX format
     * 
     * @return Current date in YYYYMMDD format
     */
    public String getCurrentDateIwtxFormat() {
        return LocalDate.now().format(IWTX_FORMAT);
    }
    
    /**
     * Get current date in user format
     * 
     * @return Current date in YYYY-MM-DD format
     */
    public String getCurrentDateUserFormat() {
        return LocalDate.now().format(USER_FORMAT);
    }
}
