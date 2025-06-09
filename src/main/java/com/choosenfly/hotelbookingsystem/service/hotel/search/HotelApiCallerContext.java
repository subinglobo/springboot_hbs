package com.choosenfly.hotelbookingsystem.service.hotel.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class HotelApiCallerContext {

	private final Map<String, List<HotelSearchApiCaller>> agentApiCallers = new HashMap<>();

    public HotelApiCallerContext(List<HotelSearchApiCaller> allCallers) {
        // Example static config. Replace with dynamic logic if needed.
        agentApiCallers.put("1", allCallers); // Enable all APIs for agent 1
    }

    public List<HotelSearchApiCaller> getCallersForAgent(Long agentId) {
        return agentApiCallers.getOrDefault(agentId, Collections.emptyList());
    }
}
