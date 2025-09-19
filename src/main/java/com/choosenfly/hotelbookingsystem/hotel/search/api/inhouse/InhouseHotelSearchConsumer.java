package com.choosenfly.hotelbookingsystem.hotel.search.api.inhouse;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.SearchMessage;
import com.choosenfly.hotelbookingsystem.hotel.search.service.impl.InhouseHotelSearchApiCaller;

@Component
public class InhouseHotelSearchConsumer {

    @Autowired
    private InhouseHotelSearchApiCaller inhouseHotelSearchApiCaller;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String REDIS_KEY_PREFIX = "hotel_search:";

    @RabbitListener(queues = "hotel.api.inhouse.queue")
    public void consumeSearchRequest(SearchMessage message) {
        try {
            String searchId = message.getSearchId();
            HotelSearchRequest request = message.getSearchRequest();

            System.out.println("=== INHOUSE CONSUMER STARTED ===");
            System.out.println("Inside INHOUSE consumer for searchId: " + searchId);
            System.out.println("Request details - CountryId: " + request.getDestinationCountryId() + 
                             ", CityId: " + request.getDestinationCityId() + 
                             ", AgentId: " + request.getAgentId());
            
            // Set status to processing
            String statusKey = REDIS_KEY_PREFIX + searchId + ":inhouse:status";
            redisTemplate.opsForValue().set(statusKey, "PROCESSING");
            
            List<HotelSearchResult> results = inhouseHotelSearchApiCaller.callApi(request);

            if (results != null && !results.isEmpty()) {
                String redisKey = REDIS_KEY_PREFIX + searchId;    
                System.out.println("INHOUSE: Storing " + results.size() + " results in Redis with key: " + redisKey);
                
                // Log each result being stored
                for (HotelSearchResult result : results) {
                    System.out.println("INHOUSE: Storing result - " + result.getHotelCode() + " - " + result.getHotelName());
                }
                
                redisTemplate.opsForList().rightPushAll(redisKey, results.toArray());
                redisTemplate.expire(redisKey, 10, java.util.concurrent.TimeUnit.MINUTES);
                
                // Verify storage
                Long listSize = redisTemplate.opsForList().size(redisKey);
                System.out.println("INHOUSE: Redis list size after storage: " + listSize);
                
                // Set status to completed
                redisTemplate.opsForValue().set(statusKey, "COMPLETED");
                
                // Increment finished counter
                redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + searchId + ":finished", 1);
                
                System.out.println("INHOUSE search completed for searchId: " + searchId + " with " + results.size() + " results");
            } else {
                // Set status to completed even if no results
                redisTemplate.opsForValue().set(statusKey, "COMPLETED");
                redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + searchId + ":finished", 1);
                
                System.out.println("INHOUSE search completed for searchId: " + searchId + " with no results");
            }
        } catch (Exception e) {
            System.err.println("Error processing INHOUSE search request for searchId: " + message.getSearchId() + " - " + e.getMessage());
            e.printStackTrace();
            
            // Set status to error
            String statusKey = REDIS_KEY_PREFIX + message.getSearchId() + ":inhouse:status";
            redisTemplate.opsForValue().set(statusKey, "ERROR");
            
            // Still increment finished counter to avoid hanging
            redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + message.getSearchId() + ":finished", 1);
            
            throw new RuntimeException("Failed to process INHOUSE search request", e); // Trigger dead-letter queue
        }
    }
}
