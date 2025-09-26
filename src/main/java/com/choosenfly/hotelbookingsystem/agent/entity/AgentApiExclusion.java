package com.choosenfly.hotelbookingsystem.agent.entity;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "agent_api_exclusion", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"agent_id", "external_api_id"})) 
public class AgentApiExclusion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne  
    @JoinColumn(name = "agent_id", referencedColumnName = "id", nullable = false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "external_api_id", referencedColumnName = "id", nullable = false)
    private ExternalApi externalApi;

    @Column(name = "is_excluded", nullable = false)
    private Boolean isExcluded = true;

    @Column(name = "exclusion_reason", length = 500)
    private String exclusionReason;

    // Default constructor
    public AgentApiExclusion() {
    }

    // Constructor with parameters
    public AgentApiExclusion(Agent agent, ExternalApi externalApi, Boolean isExcluded, String exclusionReason) {
        this.agent = agent;
        this.externalApi = externalApi;
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

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public ExternalApi getExternalApi() {
        return externalApi;
    }

    public void setExternalApi(ExternalApi externalApi) {
        this.externalApi = externalApi;
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
        return "AgentApiExclusion [id=" + id + ", agent=" + agent + ", externalApi=" + externalApi
                + ", isExcluded=" + isExcluded + ", exclusionReason=" + exclusionReason + "]";
    }
}
