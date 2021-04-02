package com.paulk.demo.interceptor;

import com.paulk.demo.config.DemoApplicationConfig;
import com.paulk.demo.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An {@link HandlerInterceptor} which calls the {@link JwtTokenService#updateExpirationDateToken(String, Integer)} to keep the token alive.
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    DemoApplicationConfig demoApplicationConfig;

    /**
     * An {@link Override} for the preHandle method to implement the {@link JwtTokenService#updateExpirationDateToken(String, Integer)} method.
     *
     * @param request  - The {@link HttpServletRequest} being processed.
     * @param response - The {@link HttpServletResponse} being processed.
     * @param handler  - The {@link Object} handler being processed.
     * @return A boolean, always true as we want to update and continue.
     * @throws Exception an {@link Exception} thrown.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(AUTHORIZATION);
        // Check Condition:
        // 1. Not null.
        // 2. Check if in format "Bearer X"
        // Otherwise skip.
        if (token == null || !token.startsWith("Bearer ")) {
            return true;
        }

        // Update the Bearer Access Token.
        String newToken = JwtTokenService.updateExpirationDateToken(token, demoApplicationConfig.getDefaultTokenTimeLimitInMilliseconds());
        response.setHeader(AUTHORIZATION, newToken);
        response.setHeader("Access-control-expose-headers", AUTHORIZATION);

        return true;

    }
}
