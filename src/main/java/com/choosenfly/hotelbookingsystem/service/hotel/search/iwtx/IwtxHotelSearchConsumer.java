package com.choosenfly.hotelbookingsystem.service.hotel.search.iwtx;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.SearchMessage;
import com.choosenfly.hotelbookingsystem.service.hotel.search.HotelSearchApiCaller;

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

            System.out.println("Inside iwtx consumer");
            List<HotelSearchResult> results = iwtxHotelSearchApiCaller.callApi(request);

            if (results != null && !results.isEmpty()) {
                String redisKey = REDIS_KEY_PREFIX + searchId;
                
                System.out.println("redis key :: "+redisKey);
                
                redisTemplate.opsForList().rightPushAll(redisKey, results.toArray());
                redisTemplate.expire(redisKey, 10, java.util.concurrent.TimeUnit.MINUTES);
                redisTemplate.opsForValue().increment(REDIS_KEY_PREFIX + searchId + ":finished", 1);
            }
        } catch (Exception e) {
            System.err.println("Error processing search request for searchId: " + message.getSearchId() + " - " + e.getMessage());
            throw new RuntimeException("Failed to process IWTX search request", e); // Trigger dead-letter queue
        }
    }
}