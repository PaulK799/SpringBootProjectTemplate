package com.paulk.demo.config;

import com.paulk.demo.DemoApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Configures the {@link RedisCacheConfig} from properties pulled from {@link DemoApplicationConfig}.
 */
@Configuration("CacheConfig")
public class RedisCacheConfig implements CacheConfig {

    private final DemoApplicationConfig demoApplicationConfig;

    /**
     * Constructor for {@link RedisCacheConfig}.
     *
     * @param demoApplicationConfig - The {@link DemoApplicationConfig} being processed.
     */
    public RedisCacheConfig(DemoApplicationConfig demoApplicationConfig) {
        this.demoApplicationConfig = demoApplicationConfig;
    }

    /**
     * Builds the {@link RedisConnectionFactory} for pulling configuration from the {@link DemoApplicationConfig}.
     *
     * @return The {@link RedisConnectionFactory} for the {@link DemoApplication}.
     */
    @Bean
    @Override
    public RedisConnectionFactory getConnectionFactory() {
        RedisConnectionFactory factory = null;
        String redisPassword = demoApplicationConfig.getPassword();

        try {
            RedisStandaloneConfiguration redisProperties = new RedisStandaloneConfiguration();

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

            factory = new LettuceConnectionFactory(redisProperties);

            return factory;
        } catch (Exception ex) {
            LOGGER.error("Error creating RedisConnectionFactory", ex);
        }

        return factory;
    }
}
