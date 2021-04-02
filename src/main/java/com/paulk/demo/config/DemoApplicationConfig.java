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

    @Value("${api.response.default.pageSize:10}")
    private Integer defaultPageSize;

    @Value("${api.response.default.pageNumber:0}")
    private Integer defaultPageNumber;

    @Value("${api.authentication.token.timelimit.minutes:30}")
    private Integer defaultTokenTimeLimitInMinutes;

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

    /**
     * Gets the Default PageNumber for the {@link DemoApplicationConfig}.
     *
     * @return The an {@link Integer} representing the pageNumber..
     */
    public Integer getDefaultPageNumber() {
        return defaultPageNumber;
    }

    /**
     * Gets the Default PageNumber for the {@link DemoApplicationConfig}.
     *
     * @return The an {@link Integer} representing the pageNumber..
     */
    public Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    /**
     * Gets the defaultTokenTimeLimitInMinutes for the {@link DemoApplicationConfig}.
     *
     * @return The an {@link Integer} representing the defaultTokenTimeLimitInMinutes.
     */
    public Integer getDefaultTokenTimeLimitInMinutes() {
        return defaultTokenTimeLimitInMinutes;
    }

    /**
     * Gets the defaultTokenTimeLimitInMinutes for the {@link DemoApplicationConfig}.
     *
     * @return The an {@link Integer} representing the defaultTokenTimeLimitInMinutes.
     */
    public Integer getDefaultTokenTimeLimitInMilliseconds() {
        return getDefaultTokenTimeLimitInMinutes() * 60 * 1000;
    }
}
