package com.choosenfly.hotelbookingsystem.agent.entity;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "external_api")
public class ExternalApi extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "api_code", unique = true, nullable = false, length = 50)
    private String apiCode;

    @Column(name = "api_name", nullable = false, length = 100)
    private String apiName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_external", nullable = false)
    private Boolean isExternal = true;

    // Default constructor
    public ExternalApi() {
    }

    // Constructor with parameters
    public ExternalApi(String apiCode, String apiName, String description, Boolean isActive, Boolean isExternal) {
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
        return "ExternalApi [id=" + id + ", apiCode=" + apiCode + ", apiName=" + apiName + ", description="
                + description + ", isActive=" + isActive + ", isExternal=" + isExternal + "]";
    }
}
