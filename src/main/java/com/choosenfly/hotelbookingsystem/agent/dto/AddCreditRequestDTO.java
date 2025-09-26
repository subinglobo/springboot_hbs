package com.choosenfly.hotelbookingsystem.agent.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class AddCreditRequestDTO {

    @NotNull(message = "Agent ID is required")
    private Long agentId;

    @NotNull(message = "Additional credit amount is required")
    @DecimalMin(value = "0.01", message = "Additional credit must be greater than 0")
    private BigDecimal additionalCredit;

    private String remarks;

    // Default constructor
    public AddCreditRequestDTO() {
    }

    // Constructor with parameters
    public AddCreditRequestDTO(Long agentId, BigDecimal additionalCredit, String remarks) {
        this.agentId = agentId;
        this.additionalCredit = additionalCredit;
        this.remarks = remarks;
    }

    // Getters and Setters
    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public BigDecimal getAdditionalCredit() {
        return additionalCredit;
    }

    public void setAdditionalCredit(BigDecimal additionalCredit) {
        this.additionalCredit = additionalCredit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "AddCreditRequestDTO [agentId=" + agentId + ", additionalCredit=" + additionalCredit + ", remarks="
                + remarks + "]";
    }
}
