package com.paulk.demo.config;

import com.paulk.demo.interceptor.RequestLoggingInterceptor;
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
    protected RequestLoggingInterceptor requestLoggingInterceptor;

    /**
     * Add an {@link RequestLoggingInterceptor} to the {@link InterceptorRegistry}.
     *
     * @param registry - The {@link InterceptorRegistry} to be processed.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggingInterceptor);
    }
}
