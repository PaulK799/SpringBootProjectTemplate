package com.paulk.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Interface to connect to Redis.
 */
@FunctionalInterface
public interface CacheConfig {
    Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    /**
     * Spring-data-redis Redis Cache. Replace if using alternative cache.
     * Return the RedisConnectionFactory required by the RedisTemplate.
     *
     * @return RedisConnectionFactory
     */
    RedisConnectionFactory getConnectionFactory();

    /**
     * Spring-data-redis Redis Cache. Replace if using alternative cache.
     *
     * @return The Redis template
     */
    @Bean
    default RedisTemplate<String, Object> getRedisTemplate() {
        RedisTemplate<String, Object> template = null;
        try {
            RedisConnectionFactory factory = getConnectionFactory();
            if (factory != null) {
                template = new RedisTemplate<>();
                template.setConnectionFactory(factory);
                template.setKeySerializer(new StringRedisSerializer());
                template.setValueSerializer(new StringRedisSerializer());
                template.setHashKeySerializer(new StringRedisSerializer());
                template.setHashValueSerializer(new StringRedisSerializer());
            } else {
                LOGGER.error("Failed to connect to Redis.");
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to initialise Redis template.", ex);
        }
        return template;
    }
}
