package com.choosenfly.hotelbookingsystem.agent.dto;

public class AgentApiExclusionResponseDTO {

    private Long id;
    private Long agentId;
    private String agentName;
    private String apiCode;
    private String apiName;
    private Boolean isExcluded;
    private String exclusionReason;

    // Default constructor
    public AgentApiExclusionResponseDTO() {
    }

    // Constructor with parameters
    public AgentApiExclusionResponseDTO(Long id, Long agentId, String agentName, String apiCode, String apiName,
                                      Boolean isExcluded, String exclusionReason) {
        this.id = id;
        this.agentId = agentId;
        this.agentName = agentName;
        this.apiCode = apiCode;
        this.apiName = apiName;
        this.isExcluded = isExcluded;
        this.exclusionReason = exclusionReason;
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

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
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
        return "AgentApiExclusionResponseDTO [id=" + id + ", agentId=" + agentId + ", agentName=" + agentName
                + ", apiCode=" + apiCode + ", apiName=" + apiName + ", isExcluded=" + isExcluded
                + ", exclusionReason=" + exclusionReason + "]";
    }
}
