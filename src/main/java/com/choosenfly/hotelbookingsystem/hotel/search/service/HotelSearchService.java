package com.choosenfly.hotelbookingsystem.hotel.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.configuration.HotelRabbitMQProperties;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.SearchMessage;
import com.choosenfly.hotelbookingsystem.inventory.hotel.repository.ResultRepository;

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
        System.out.println("enabled callers :: "+enabledCallers);
        
        Map<String, String> routingKeys = rabbitMQProperties.getRoutingkey() != null ? rabbitMQProperties.getRoutingkey() : Map.of();

        System.out.println("routingKeys :: "+routingKeys);
        
        for (HotelSearchApiCaller caller : enabledCallers) {
            String apiKey = caller.getApiKey();
            SearchMessage message = new SearchMessage(searchId, request, apiKey);
            String routingKey = routingKeys.getOrDefault(apiKey, "hotel.api." + apiKey + ".routingKey");
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
}