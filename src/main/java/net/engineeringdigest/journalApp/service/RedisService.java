package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;   // Specify types

    private final ObjectMapper objectMapper = new ObjectMapper(); // Reuse ObjectMapper

    public <T> T get(String key, Class<T> entityClass) {
        try {
            String json = redisTemplate.opsForValue().get(key);
            if (json == null) {
                log.warn("Cache miss for key: {}", key);
                return null;
            }
            return objectMapper.readValue(json, entityClass);
        } catch (Exception e) {
            log.error("Error while getting data from Redis for key {}: {}", key, e.getMessage(), e);
            return null;
        }
    }


    public void set(String key, Object value, Long ttl) {
        try {
            String json = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json, ttl, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error("Error while serializing object to JSON for Redis key {}: {}", key, e.getMessage(), e);
        }
    }

}


