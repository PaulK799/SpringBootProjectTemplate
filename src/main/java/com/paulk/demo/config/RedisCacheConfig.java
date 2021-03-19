package com.paulk.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration("CacheConfig")
public class RedisCacheConfig implements CacheConfig {

    private final DemoApplicationConfig demoApplicationConfig;

    /**
     *
     * Constructor for {@link RedisCacheConfig}.
     *
     * @param demoApplicationConfig - The {@link DemoApplicationConfig} being processed.
     */
    public RedisCacheConfig(DemoApplicationConfig demoApplicationConfig) {
        this.demoApplicationConfig = demoApplicationConfig;
    }

    @Bean
    @Override
    public RedisConnectionFactory getConnectionFactory() {
        RedisConnectionFactory factory = null;
        String redisPassword = demoApplicationConfig.getPassword();

        try {
            RedisStandaloneConfiguration redisProperties = new RedisStandaloneConfiguration();
            factory = new JedisConnectionFactory(redisProperties);

            // Set Password
            if (redisPassword != null && !redisPassword.isEmpty()) {
                redisProperties.setPassword(RedisPassword.of(redisPassword));
            }

            // Set Hostname
            String hostname = demoApplicationConfig.getHostname();
            if (hostname != null && !hostname.isEmpty()) {
                redisProperties.setHostName(hostname);
            }

            // Set Port
            redisProperties.setPort(demoApplicationConfig.getPort());

            return factory;
        } catch (Exception ex) {
            LOGGER.error("Error creating RedisConnectionFactory", ex);
        }

        return factory;
    }
}
