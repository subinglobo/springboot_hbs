package com.choosenfly.hotelbookingsystem.api.hotelroom.util;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.CancellationPolicy;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Utility class to generate human-readable cancellation policy text
 */
public class CancellationPolicyTextGenerator {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    /**
     * Generate human-readable cancellation policy text from cancellation policies
     * 
     * @param cancellationPolicies List of cancellation policies
     * @return Human-readable cancellation policy text
     */
    public static String generateCancellationPolicyText(List<CancellationPolicy> cancellationPolicies) {
        if (cancellationPolicies == null || cancellationPolicies.isEmpty()) {
            return "No cancellation policy available";
        }

        StringBuilder policyText = new StringBuilder();
        
        for (int i = 0; i < cancellationPolicies.size(); i++) {
            CancellationPolicy policy = cancellationPolicies.get(i);
            
            if (policy.getFromDate() != null && policy.getToDate() != null) {
                String fromDateFormatted = formatDate(policy.getFromDate());
                String toDateFormatted = formatDate(policy.getToDate());
                
                if (policy.getValue() != null && policy.getValue().compareTo(BigDecimal.ZERO) == 0) {
                    policyText.append("Free cancellation until ").append(fromDateFormatted);
                } else {
                    policyText.append("Cancellation from ").append(fromDateFormatted)
                              .append(" to ").append(toDateFormatted);
                    
                    if (policy.getValue() != null) {
                        if ("PERCENT".equalsIgnoreCase(policy.getPercentOrAmount())) {
                            policyText.append(" will be charged ").append(policy.getValue()).append("%");
                        } else {
                            policyText.append(" will be charged ").append(policy.getValue()).append(" AED");
                        }
                    }
                }
                
                if (i < cancellationPolicies.size() - 1) {
                    policyText.append(". ");
                }
            }
        }
        
        return policyText.toString();
    }

    /**
     * Generate individual policy text for a single cancellation policy
     * 
     * @param policy Single cancellation policy
     * @return Human-readable policy text for this specific policy
     */
    public static String generateIndividualPolicyText(CancellationPolicy policy) {
        if (policy == null || policy.getFromDate() == null || policy.getToDate() == null) {
            return "No policy details available";
        }

        String fromDateFormatted = formatDate(policy.getFromDate());
        String toDateFormatted = formatDate(policy.getToDate());
        
        if (policy.getValue() != null && policy.getValue().compareTo(BigDecimal.ZERO) == 0) {
            return "Free cancellation until " + fromDateFormatted;
        } else {
            StringBuilder text = new StringBuilder();
            text.append("Cancellation from ").append(fromDateFormatted)
                .append(" - ").append(toDateFormatted)
                .append(" will be charged ");
            
            if (policy.getValue() != null) {
                if ("PERCENT".equalsIgnoreCase(policy.getPercentOrAmount())) {
                    text.append(policy.getValue()).append("%");
                } else {
                    text.append(policy.getValue()).append(" AED");
                }
            } else {
                text.append("0 AED");
            }
            
            return text.toString();
        }
    }

    /**
     * Format charge text based on value and type
     */
    private static String formatChargeText(BigDecimal value, String percentOrAmount) {
        if ("P".equalsIgnoreCase(percentOrAmount)) {
            return String.format("%.0f%% of total booking amount", value);
        } else {
            return String.format("AED %.2f", value);
        }
    }

    /**
     * Format date from YYYY-MM-DD to readable format
     */
    private static String formatDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        
        try {
            LocalDate date = LocalDate.parse(dateStr, INPUT_FORMAT);
            return date.format(OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            return dateStr; // Return original if parsing fails
        }
    }

    /**
     * Check if date is in the past (before today)
     */
    private static boolean isDateInPast(String dateStr) {
        if (dateStr == null) {
            return false;
        }
        
        try {
            LocalDate date = LocalDate.parse(dateStr, INPUT_FORMAT);
            return date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
