package com.choosenfly.hotelbookingsystem.api.hotelroom.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for currency conversion
 */
public class CurrencyConverter {

    // Fixed USD to AED conversion rate (1 USD = 3.67 AED approximately)
    // In production, this should be fetched from a real-time currency API
    private static final BigDecimal USD_TO_AED_RATE = new BigDecimal("3.67");

    /**
     * Convert USD amount to AED
     * 
     * @param usdAmount Amount in USD
     * @return Amount in AED, rounded to 2 decimal places
     */
    public static BigDecimal convertUsdToAed(BigDecimal usdAmount) {
        if (usdAmount == null) {
            return null;
        }
        return usdAmount.multiply(USD_TO_AED_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Convert USD amount to AED
     * 
     * @param usdAmount Amount in USD as double
     * @return Amount in AED, rounded to 2 decimal places
     */
    public static BigDecimal convertUsdToAed(double usdAmount) {
        return convertUsdToAed(BigDecimal.valueOf(usdAmount));
    }

    /**
     * Get the current USD to AED conversion rate
     * 
     * @return Current conversion rate
     */
    public static BigDecimal getUsdToAedRate() {
        return USD_TO_AED_RATE;
    }
}
