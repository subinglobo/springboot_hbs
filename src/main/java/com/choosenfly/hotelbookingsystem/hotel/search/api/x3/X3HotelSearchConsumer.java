package com.choosenfly.hotelbookingsystem.hotel.search.api.x3;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.SearchMessage;
import com.choosenfly.hotelbookingsystem.hotel.search.service.HotelSearchApiCaller;

@Component
public class X3HotelSearchConsumer {

    @Autowired
    @Qualifier("x3HotelSearchApiCaller")
    private HotelSearchApiCaller x3HotelSearchApiCaller;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String REDIS_KEY_PREFIX = "hotel_search:";

    @RabbitListener(queues = "hotel.api.x3.queue")
    public void consumeSearchRequest(SearchMessage message) {
        try {
            String searchId = message.getSearchId();
            HotelSearchRequest request = message.getSearchRequest();

            System.out.println("Inside X3 consumer for searchId: " + searchId);
            
            // Set status to processing
            String statusKey = REDIS_KEY_PREFIX + searchId + ":x3:status";
            redisTemplate.opsForValue().set(statusKey, "PROCESSING");
            
            List<HotelSearchResult> results = x3HotelSearchApiCaller.callApi(request);

            if (results != null && !results.isEmpty()) {
                String redisKey = REDIS_KEY_PREFIX + searchId;    
                redisTemplate.opsForList().rightPushAll(redisKey, results.toArray());
                redisTemplate.expire(redisKey, 10, java.util.concurrent.TimeUnit.MINUTES);
                
                // Set status to completed
                redisTemplate.opsForValue().set(statusKey, "COMPLETED");
                
                // Increment finished counter
                redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + searchId + ":finished", 1);
                
                System.out.println("X3 search completed for searchId: " + searchId + " with " + results.size() + " results");
            } else {
                // Set status to completed even if no results
                redisTemplate.opsForValue().set(statusKey, "COMPLETED");
                redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + searchId + ":finished", 1);
                
                System.out.println("X3 search completed for searchId: " + searchId + " with no results");
            }
        } catch (Exception e) {
            System.err.println("Error processing X3 search request for searchId: " + message.getSearchId() + " - " + e.getMessage());
            e.printStackTrace();
            
            // Set status to error
            String statusKey = REDIS_KEY_PREFIX + message.getSearchId() + ":x3:status";
            redisTemplate.opsForValue().set(statusKey, "ERROR");
            
            // Still increment finished counter to avoid hanging
            redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + message.getSearchId() + ":finished", 1);
            
            throw new RuntimeException("Failed to process X3 search request", e); // Trigger dead-letter queue
        }
    }
}
