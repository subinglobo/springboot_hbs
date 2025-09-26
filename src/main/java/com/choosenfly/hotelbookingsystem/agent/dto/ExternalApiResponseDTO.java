package com.choosenfly.hotelbookingsystem.agent.dto;

public class ExternalApiResponseDTO {

    private Long id;
    private String apiCode;
    private String apiName;
    private String description;
    private Boolean isActive;
    private Boolean isExternal;

    // Default constructor
    public ExternalApiResponseDTO() {
    }

    // Constructor with parameters
    public ExternalApiResponseDTO(Long id, String apiCode, String apiName, String description, Boolean isActive, Boolean isExternal) {
        this.id = id;
        this.apiCode = apiCode;
        this.apiName = apiName;
        this.description = description;
        this.isActive = isActive;
        this.isExternal = isExternal;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    @Override
    public String toString() {
        return "ExternalApiResponseDTO [id=" + id + ", apiCode=" + apiCode + ", apiName=" + apiName + ", description="
                + description + ", isActive=" + isActive + ", isExternal=" + isExternal + "]";
    }
}
