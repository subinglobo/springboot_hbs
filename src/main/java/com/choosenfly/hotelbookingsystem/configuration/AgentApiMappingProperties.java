package com.choosenfly.hotelbookingsystem.configuration;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "agent.api")
public class AgentApiMappingProperties {
    private Map<Long, String> mapping;

    public Map<Long, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<Long, String> mapping) {
        this.mapping = mapping;
    }

    @Override
    public String toString() {
        return "AgentApiMappingProperties{mapping=" + mapping + "}";
    }
}