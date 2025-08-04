package com.choosenfly.hotelbookingsystem.inventory.hotel.repository;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;

@Repository
public class ResultRepository {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String PREFIX = "hotel_search:";

    public void saveResult(String searchId, String apiKey, HotelSearchResult result) {
        String key = PREFIX + searchId;
        redisTemplate.opsForHash().put(key, apiKey, result);
        redisTemplate.expire(key, Duration.ofMinutes(5)); // TTL to auto-expire
    }

    public List<HotelSearchResult> getResults(String searchId) {
        String key = PREFIX + searchId;
        System.out.println("key : " + key);

        try {
            List<Object> objects = redisTemplate.opsForList().range(key, 0, -1);
            System.out.println("entries::" + objects);

            return objects.stream()
                          .map(obj -> (HotelSearchResult) obj)
                          .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
