package com.choosenfly.hotelbookingsystem.util.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;

@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, HotelSearchResult> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, HotelSearchResult> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		// Optional: use JSON serialization
		GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
		template.setDefaultSerializer(serializer);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(serializer);
		template.afterPropertiesSet();

		return template;
	}
}
