package com.choosenfly.hotelbookingsystem.agent.dto;

import jakarta.validation.constraints.NotNull;

public class AgentApiExclusionRequestDTO {

    @NotNull(message = "Agent ID is required")
    private Long agentId;

    @NotNull(message = "API code is required")
    private String apiCode;

    private Boolean isExcluded = true;

    private String exclusionReason;

    // Default constructor
    public AgentApiExclusionRequestDTO() {
    }

    // Constructor with parameters
    public AgentApiExclusionRequestDTO(Long agentId, String apiCode, Boolean isExcluded, String exclusionReason) {
        this.agentId = agentId;
        this.apiCode = apiCode;
        this.isExcluded = isExcluded;
        this.exclusionReason = exclusionReason;
    }

    // Getters and Setters
    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public Boolean getIsExcluded() {
        return isExcluded;
    }

    public void setIsExcluded(Boolean isExcluded) {
        this.isExcluded = isExcluded;
    }

    public String getExclusionReason() {
        return exclusionReason;
    }

    public void setExclusionReason(String exclusionReason) {
        this.exclusionReason = exclusionReason;
    }

    @Override
    public String toString() {
        return "AgentApiExclusionRequestDTO [agentId=" + agentId + ", apiCode=" + apiCode + ", isExcluded="
                + isExcluded + ", exclusionReason=" + exclusionReason + "]";
    }
}
