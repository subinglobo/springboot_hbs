package com.choosenfly.hotelbookingsystem.agent.entity;

import java.math.BigDecimal;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "agent_credit_limit")
public class AgentCreditLimit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    private Agent agent;

    @Column(name = "total_credit_limit", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalCreditLimit;

    @Column(name = "available_credit_limit", precision = 15, scale = 2, nullable = false)
    private BigDecimal availableCreditLimit;

    // This field is calculated dynamically and not stored in DB
    @Transient
    private BigDecimal usedCreditLimit;

    // Default constructor
    public AgentCreditLimit() {
    }

    // Constructor with parameters
    public AgentCreditLimit(Agent agent, BigDecimal totalCreditLimit, BigDecimal availableCreditLimit) {
        this.agent = agent;
        this.totalCreditLimit = totalCreditLimit;
        this.availableCreditLimit = availableCreditLimit;
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

    // Calculated field - dynamically computed
    public BigDecimal getUsedCreditLimit() {
        if (totalCreditLimit != null && availableCreditLimit != null) {
            return totalCreditLimit.subtract(availableCreditLimit);
        }
        return BigDecimal.ZERO;
    }

    public void setUsedCreditLimit(BigDecimal usedCreditLimit) {
        // This setter is provided for JSON serialization but doesn't store in DB
        this.usedCreditLimit = usedCreditLimit;
    }

    @Override
    public String toString() {
        return "AgentCreditLimit [id=" + id + ", agent=" + agent + ", totalCreditLimit=" + totalCreditLimit
                + ", availableCreditLimit=" + availableCreditLimit + ", usedCreditLimit=" + getUsedCreditLimit() + "]";
    }
}
