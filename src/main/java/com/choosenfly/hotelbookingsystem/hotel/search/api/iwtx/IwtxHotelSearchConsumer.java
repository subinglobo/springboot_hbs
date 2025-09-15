package com.choosenfly.hotelbookingsystem.hotel.search.api.iwtx;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.SearchMessage;
import com.choosenfly.hotelbookingsystem.hotel.search.service.HotelSearchApiCaller;

@Component
public class IwtxHotelSearchConsumer {

    @Autowired
    private HotelSearchApiCaller iwtxHotelSearchApiCaller;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String REDIS_KEY_PREFIX = "hotel_search:";

    @RabbitListener(queues = "hotel.api.iwtx.queue")
    public void consumeSearchRequest(SearchMessage message) {
        try {
            String searchId = message.getSearchId();
            HotelSearchRequest request = message.getSearchRequest();

            System.out.println("Inside IWTX consumer for searchId: " + searchId);
            
            // Set status to processing
            String statusKey = REDIS_KEY_PREFIX + searchId + ":iwtx:status";
            redisTemplate.opsForValue().set(statusKey, "PROCESSING");
            
            List<HotelSearchResult> results = iwtxHotelSearchApiCaller.callApi(request);

            if (results != null && !results.isEmpty()) {
                String redisKey = REDIS_KEY_PREFIX + searchId;    
                redisTemplate.opsForList().rightPushAll(redisKey, results.toArray());
                redisTemplate.expire(redisKey, 10, java.util.concurrent.TimeUnit.MINUTES);
                
                // Set status to completed
                redisTemplate.opsForValue().set(statusKey, "COMPLETED");
                
                // Increment finished counter
                redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + searchId + ":finished", 1);
                
                System.out.println("IWTX search completed for searchId: " + searchId + " with " + results.size() + " results");
            } else {
                // Set status to completed even if no results
                redisTemplate.opsForValue().set(statusKey, "COMPLETED");
                redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + searchId + ":finished", 1);
                
                System.out.println("IWTX search completed for searchId: " + searchId + " with no results");
            }
        } catch (Exception e) {
            System.err.println("Error processing IWTX search request for searchId: " + message.getSearchId() + " - " + e.getMessage());
            e.printStackTrace();
            
            // Set status to error
            String statusKey = REDIS_KEY_PREFIX + message.getSearchId() + ":iwtx:status";
            redisTemplate.opsForValue().set(statusKey, "ERROR");
            
            // Still increment finished counter to avoid hanging
            redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + message.getSearchId() + ":finished", 1);
            
            throw new RuntimeException("Failed to process IWTX search request", e); // Trigger dead-letter queue
        }
    }
}