package com.choosenfly.hotelbookingsystem.repository.hotel;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;

@Repository
public class ResultRepository {


    @Autowired
    private RedisTemplate<String, HotelSearchResult> redisTemplate;

    private static final String PREFIX = "hotelSearch:";

    public void saveResult(String searchId, String apiKey, HotelSearchResult result) {
        String key = PREFIX + searchId;
        redisTemplate.opsForHash().put(key, apiKey, result);
        redisTemplate.expire(key, Duration.ofMinutes(5)); // TTL to auto-expire
    }

    public List<HotelSearchResult> getResults(String searchId) {
        String key = PREFIX + searchId;
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        return entries.values().stream()
            .map(obj -> (HotelSearchResult) obj)
            .collect(Collectors.toList());
    }
}
