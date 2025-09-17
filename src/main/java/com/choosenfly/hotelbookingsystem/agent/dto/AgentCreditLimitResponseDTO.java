package com.choosenfly.hotelbookingsystem.agent.dto;

import java.math.BigDecimal;

public class AgentCreditLimitResponseDTO {

    private Long id;
    private Long agentId;
    private String agentName;
    private BigDecimal totalCreditLimit;
    private BigDecimal availableCreditLimit;
    private BigDecimal usedCreditLimit;

    // Default constructor
    public AgentCreditLimitResponseDTO() {
    }

    // Constructor with parameters
    public AgentCreditLimitResponseDTO(Long id, Long agentId, String agentName, 
                                     BigDecimal totalCreditLimit, BigDecimal availableCreditLimit, 
                                     BigDecimal usedCreditLimit) {
        this.id = id;
        this.agentId = agentId;
        this.agentName = agentName;
        this.totalCreditLimit = totalCreditLimit;
        this.availableCreditLimit = availableCreditLimit;
        this.usedCreditLimit = usedCreditLimit;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public BigDecimal getUsedCreditLimit() {
        return usedCreditLimit;
    }

    public void setUsedCreditLimit(BigDecimal usedCreditLimit) {
        this.usedCreditLimit = usedCreditLimit;
    }

    @Override
    public String toString() {
        return "AgentCreditLimitResponseDTO [id=" + id + ", agentId=" + agentId + ", agentName=" + agentName
                + ", totalCreditLimit=" + totalCreditLimit + ", availableCreditLimit=" + availableCreditLimit
                + ", usedCreditLimit=" + usedCreditLimit + "]";
    }
}
