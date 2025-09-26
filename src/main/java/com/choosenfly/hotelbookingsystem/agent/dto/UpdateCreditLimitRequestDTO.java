package com.choosenfly.hotelbookingsystem.agent.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class UpdateCreditLimitRequestDTO {

    @NotNull(message = "Agent ID is required")
    private Long agentId;

    @NotNull(message = "Total credit limit is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total credit limit must be greater than 0")
    private BigDecimal totalCreditLimit;

    @NotNull(message = "Available credit limit is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Available credit limit must be greater than or equal to 0")
    private BigDecimal availableCreditLimit;

    // Default constructor
    public UpdateCreditLimitRequestDTO() {
    }

    // Constructor with parameters
    public UpdateCreditLimitRequestDTO(Long agentId, BigDecimal totalCreditLimit, BigDecimal availableCreditLimit) {
        this.agentId = agentId;
        this.totalCreditLimit = totalCreditLimit;
        this.availableCreditLimit = availableCreditLimit;
    }

    // Getters and Setters
    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public BigDecimal getTotalCreditLimit() {
        return totalCreditLimit;
    }

    public void setTotalCreditLimit(BigDecimal totalCreditLimit) {
        this.totalCreditLimit = totalCreditLimit;
    }

    public BigDecimal getAvailableCreditLimit() {
        return availableCreditLimit;
    }

    public void setAvailableCreditLimit(BigDecimal availableCreditLimit) {
        this.availableCreditLimit = availableCreditLimit;
    }

    @Override
    public String toString() {
        return "UpdateCreditLimitRequestDTO [agentId=" + agentId + ", totalCreditLimit=" + totalCreditLimit
                + ", availableCreditLimit=" + availableCreditLimit + "]";
    }
}
