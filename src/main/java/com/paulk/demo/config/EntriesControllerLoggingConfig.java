package com.paulk.demo.config;

import com.paulk.demo.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Override the {@link WebMvcConfigurer} to add an Interceptor to the {@link InterceptorRegistry}.
 */
@Configuration
public class EntriesControllerLoggingConfig implements WebMvcConfigurer {

    @Autowired
    protected LoggingInterceptor loggingInterceptor;

    /**
     * Add an {@link LoggingInterceptor} to the {@link InterceptorRegistry}.
     *
     * @param registry - The {@link InterceptorRegistry} to be processed.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}
