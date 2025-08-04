package com.choosenfly.hotelbookingsystem.hotel.search.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.choosenfly.hotelbookingsystem.configuration.AgentApiMappingProperties;

import jakarta.annotation.PostConstruct;

@Component
public class HotelApiCallerContext {

    private static final Logger logger = LoggerFactory.getLogger(HotelApiCallerContext.class);
    private final Map<Long, List<HotelSearchApiCaller>> agentApiCallers = new HashMap<>();

    @Autowired
    private List<HotelSearchApiCaller> allCallers;

    @Autowired
    private AgentApiMappingProperties agentApiMappingProperties;

    @PostConstruct
    public void initializeCallers() {
        Map<Long, String> agentApiMapping = agentApiMappingProperties.getMapping();
        logger.info("Injected agent.api.mapping: {}", agentApiMapping);

        if (allCallers == null || allCallers.isEmpty()) {
            logger.warn("No HotelSearchApiCaller beans found. agentApiCallers will be empty.");
            return;
        }

        Map<String, HotelSearchApiCaller> callerByKey = allCallers.stream()
                .collect(Collectors.toMap(HotelSearchApiCaller::getApiKey, c -> c));

        if (agentApiMapping == null || agentApiMapping.isEmpty()) {
            logger.warn("agent.api.mapping is null or empty. Enabling all callers for agentId=1 as fallback.");
            agentApiCallers.put(1L, allCallers);
            logger.info("Initialized agentId=1 with {} callers: {}", 
                        allCallers.size(), 
                        allCallers.stream().map(HotelSearchApiCaller::getApiKey).toList());
            return;
        }

        for (Map.Entry<Long, String> entry : agentApiMapping.entrySet()) {
            Long agentId = entry.getKey();
            String apiKeysValue = entry.getValue();
            if (apiKeysValue == null || apiKeysValue.trim().isEmpty()) {
                logger.warn("No API keys defined for agentId={}. Using all callers as fallback.", agentId);
                agentApiCallers.put(agentId, allCallers);
                continue;
            }
            List<String> apiKeys = Arrays.asList(apiKeysValue.split(","));
            List<HotelSearchApiCaller> callers = apiKeys.stream()
                    .map(callerByKey::get)
                    .filter(Objects::nonNull)
                    .toList();
            if (callers.isEmpty()) {
                logger.warn("No valid callers found for agentId={} with apiKeys={}. Using all callers as fallback.", 
                            agentId, apiKeys);
                callers = allCallers;
            }
            agentApiCallers.put(agentId, callers);
            logger.info("Initialized agentId={} with {} callers: {}", agentId, callers.size(), apiKeys);
        }
    }

    public List<HotelSearchApiCaller> getCallersForAgent(Long agentId) {
        List<HotelSearchApiCaller> callers = agentApiCallers.getOrDefault(agentId, allCallers);
        logger.debug("Returning {} callers for agentId={}: {}", 
                    callers.size(), agentId, 
                    callers.stream().map(HotelSearchApiCaller::getApiKey).toList());
        return callers;
    }
}
