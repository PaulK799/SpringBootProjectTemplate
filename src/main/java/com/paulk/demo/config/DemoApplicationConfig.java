package com.paulk.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@RefreshScope
public class DemoApplicationConfig {

    @Value("${spring.redis.host:localhost}")
    private String hostname;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.password:}")
    private String password;

    /**
     * Gets the Hostname for the {@link DemoApplicationConfig}.
     *
     * @return The Hostname.
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Gets the Port for the {@link DemoApplicationConfig}.
     *
     * @return The Hostname.
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the Password for the {@link DemoApplicationConfig}.
     *
     * @return The Hostname.
     */
    public String getPassword() {
        return password;
    }
}
