package com.choosenfly.hotelbookingsystem.agent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for updating existing external APIs in the system
 */
public class ExternalApiUpdateRequestDTO {

    @NotNull(message = "API ID is required")
    private Long id;

    @NotBlank(message = "API name is required")
    @Size(max = 100, message = "API name must not exceed 100 characters")
    private String apiName;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotNull(message = "isActive flag is required")
    private Boolean isActive;

    @NotNull(message = "isExternal flag is required")
    private Boolean isExternal;

    // Default constructor
    public ExternalApiUpdateRequestDTO() {}

    // Constructor with required fields
    public ExternalApiUpdateRequestDTO(Long id, String apiName, Boolean isActive, Boolean isExternal) {
        this.id = id;
        this.apiName = apiName;
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
        return "ExternalApiUpdateRequestDTO{" +
                "id=" + id +
                ", apiName='" + apiName + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", isExternal=" + isExternal +
                '}';
    }
}
