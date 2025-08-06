package com.choosenfly.hotelbookingsystem.agent.entity;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agent_category")
public class AgentCategory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_category_id")
    private Long agentCategoryId;

    @Column(name = "name")
    private String name;

    public Long getAgentCategoryId() {
        return agentCategoryId;
    }

    public void setAgentCategoryId(Long agentCategoryId) {
        this.agentCategoryId = agentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AgentCategory{" +
                "agentCategoryId=" + agentCategoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
