package com.choosenfly.hotelbookingsystem.api.iwtx.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.HashMap;

@Service
public class CurrencyConversionService {
    
    // Static exchange rates to AED (in a real application, this would come from an external API)
    private static final Map<String, BigDecimal> EXCHANGE_RATES_TO_AED = new HashMap<>();
    
    static {
        // Common currency rates to AED (these should be updated regularly in production)
        EXCHANGE_RATES_TO_AED.put("USD", new BigDecimal("3.67")); // 1 USD = 3.67 AED
        EXCHANGE_RATES_TO_AED.put("EUR", new BigDecimal("4.02")); // 1 EUR = 4.02 AED
        EXCHANGE_RATES_TO_AED.put("GBP", new BigDecimal("4.65")); // 1 GBP = 4.65 AED
        EXCHANGE_RATES_TO_AED.put("SAR", new BigDecimal("0.98")); // 1 SAR = 0.98 AED
        EXCHANGE_RATES_TO_AED.put("QAR", new BigDecimal("1.01")); // 1 QAR = 1.01 AED
        EXCHANGE_RATES_TO_AED.put("KWD", new BigDecimal("12.05")); // 1 KWD = 12.05 AED
        EXCHANGE_RATES_TO_AED.put("BHD", new BigDecimal("9.73")); // 1 BHD = 9.73 AED
        EXCHANGE_RATES_TO_AED.put("OMR", new BigDecimal("9.54")); // 1 OMR = 9.54 AED
        EXCHANGE_RATES_TO_AED.put("INR", new BigDecimal("0.044")); // 1 INR = 0.044 AED
        EXCHANGE_RATES_TO_AED.put("AED", new BigDecimal("1.00")); // 1 AED = 1 AED
    }
    
    /**
     * Convert amount from source currency to AED
     * 
     * @param amount The amount to convert
     * @param fromCurrency The source currency code
     * @return Converted amount in AED
     */
    public BigDecimal convertToAED(BigDecimal amount, String fromCurrency) {
        if (amount == null || fromCurrency == null) {
            return amount;
        }
        
        // If already in AED, return as is
        if ("AED".equalsIgnoreCase(fromCurrency)) {
            return amount;
        }
        
        BigDecimal exchangeRate = EXCHANGE_RATES_TO_AED.get(fromCurrency.toUpperCase());
        if (exchangeRate == null) {
            // If currency not found, return original amount (or throw exception in production)
            return amount;
        }
        
        return amount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
    }
    
    /**
     * Convert string amount from source currency to AED
     * 
     * @param amountStr The amount string to convert
     * @param fromCurrency The source currency code
     * @return Converted amount in AED as string
     */
    public String convertToAED(String amountStr, String fromCurrency) {
        if (amountStr == null || amountStr.trim().isEmpty()) {
            return amountStr;
        }
        
        try {
            BigDecimal amount = new BigDecimal(amountStr);
            BigDecimal convertedAmount = convertToAED(amount, fromCurrency);
            return convertedAmount.toString();
        } catch (NumberFormatException e) {
            // If not a valid number, return original string
            return amountStr;
        }
    }
    
    /**
     * Get the exchange rate from source currency to AED
     * 
     * @param fromCurrency The source currency code
     * @return Exchange rate to AED
     */
    public BigDecimal getExchangeRateToAED(String fromCurrency) {
        return EXCHANGE_RATES_TO_AED.get(fromCurrency.toUpperCase());
    }
    
    /**
     * Check if currency conversion is supported
     * 
     * @param currency The currency code to check
     * @return true if supported, false otherwise
     */
    public boolean isCurrencySupported(String currency) {
        return EXCHANGE_RATES_TO_AED.containsKey(currency.toUpperCase());
    }
}
