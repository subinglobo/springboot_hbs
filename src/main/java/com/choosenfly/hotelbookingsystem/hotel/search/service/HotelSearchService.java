package com.choosenfly.hotelbookingsystem.hotel.search.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.configuration.HotelRabbitMQProperties;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.SearchMessage;
import com.choosenfly.hotelbookingsystem.inventory.repository.ResultRepository;

import jakarta.annotation.PostConstruct;

@Service
public class HotelSearchService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private HotelApiCallerContext callerContext;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private HotelRabbitMQProperties rabbitMQProperties;

    @PostConstruct
    public void debugRoutingKeys() {
        System.out.println("Hotel RabbitMQ Properties: " + rabbitMQProperties);
    } 

    public String initiateSearch(HotelSearchRequest request) {
        String searchId = UUID.randomUUID().toString();
        List<HotelSearchApiCaller> enabledCallers = callerContext.getCallersForAgent(request.getAgentId());
        System.out.println("=== HOTEL SEARCH SERVICE ===");
        System.out.println("SearchId: " + searchId);
        System.out.println("AgentId: " + request.getAgentId());
        System.out.println("Enabled callers count: " + enabledCallers.size());
        for (HotelSearchApiCaller caller : enabledCallers) {
            System.out.println("- API Caller: " + caller.getApiKey());
        }
        
        Map<String, String> routingKeys = rabbitMQProperties.getRoutingkey() != null ? rabbitMQProperties.getRoutingkey() : Map.of();

        System.out.println("routingKeys :: "+routingKeys);
        
        for (HotelSearchApiCaller caller : enabledCallers) {
            String apiKey = caller.getApiKey();
            SearchMessage message = new SearchMessage(searchId, request, apiKey);
            String routingKey = routingKeys.getOrDefault(apiKey, "hotel.api." + apiKey + ".routingKey");
            System.out.println("Sending message to exchange: " + rabbitMQProperties.getExchange() + 
                             ", routingKey: " + routingKey + ", apiKey: " + apiKey);
            rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(), routingKey, message);
        }
        return searchId;
    }

    public List<HotelSearchResult> getResults(String searchId) {
        return resultRepository.getResults(searchId);
    }

    public boolean isSearchComplete(String searchId, Long agentId) {
        Integer completed = (Integer) redisTemplate.opsForValue().get("hotel_search:" + searchId + ":finished");
        List<HotelSearchApiCaller> enabledCallers = callerContext.getCallersForAgent(agentId);
        return completed != null && completed >= enabledCallers.size();
    }
    
    public Map<String, String> getSearchStatusMap(String searchId, Long agentId) {
        List<HotelSearchApiCaller> enabledCallers = callerContext.getCallersForAgent(agentId);
        Map<String, String> statusMap = new HashMap<>();

        for (HotelSearchApiCaller caller : enabledCallers) {
            String apiName = caller.getApiKey();
            String statusKey = "hotel_search:" + searchId + ":" + apiName + ":status";
            String status = (String) redisTemplate.opsForValue().get(statusKey);
            
            System.out.println("status :: "+status);
            
            statusMap.put(apiName, status != null ? status : "IN_PROGRESS");
        }

        
        System.out.println("statusMap:: "+statusMap);
        return statusMap;
    }
    
    public List<HotelSearchResult> applyFilters(List<HotelSearchResult> results, Integer starRating, String apiType) {
    	
    	return results.stream()
                .filter(r -> starRating == null || r.getStarRating() == starRating)
                .filter(r -> apiType == null || r.getApiType().equalsIgnoreCase(apiType))
                .collect(Collectors.toList());
    }

    public List<HotelSearchResult> sortResults(List<HotelSearchResult> results, String sortBy, String sortOrder) {
        Comparator<HotelSearchResult> comparator;

        switch (sortBy) {
            case "baseRate":
                comparator = Comparator.comparing(HotelSearchResult::getBaseRate, Comparator.nullsLast(Double::compareTo));
                break;
            case "starRating":
                comparator = Comparator.comparing(HotelSearchResult::getStarRating, Comparator.nullsLast(Integer::compareTo));
                break;
            case "hotelName":
                comparator = Comparator.comparing(HotelSearchResult::getHotelName, Comparator.nullsLast(String::compareTo));
                break;
            default:
                comparator = Comparator.comparing(HotelSearchResult::getBaseRate, Comparator.nullsLast(Double::compareTo));
        }

        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        return results.stream().sorted(comparator).collect(Collectors.toList());
    }
}