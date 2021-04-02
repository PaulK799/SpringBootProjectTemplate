package com.paulk.demo.security;

import com.paulk.demo.config.DemoApplicationConfig;
import com.paulk.demo.config.SecurityConfig;
import com.paulk.demo.utils.ObjectMapperInstance;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A {@link Component} which creates a JWT token when successfully Authenticated.
 */
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    protected DemoApplicationConfig demoApplicationConfig;
    protected SecurityConfig securityConfig;

    /**
     * Constructor for {@link JwtAuthenticationFilter}.
     *
     * @param securityConfig        - The {@link SecurityConfig} bean.
     * @param demoApplicationConfig - The {@link DemoApplicationConfig} bean.
     * @throws Exception An {@link Exception} thrown.
     */
    public JwtAuthenticationFilter(SecurityConfig securityConfig, DemoApplicationConfig demoApplicationConfig) throws Exception {
        this.demoApplicationConfig = demoApplicationConfig;
        this.securityConfig = securityConfig;

        // Authentication Manager from Bean.
        this.setAuthenticationManager(securityConfig.authenticationManagerBean());
    }

    /**
     * Override of the successfulAuthentication provided in {@link UsernamePasswordAuthenticationFilter} to create a JWT Token.
     *
     * @param request    - The {@link HttpServletRequest} being processed.
     * @param response   - The {@link HttpServletResponse} being processed.
     * @param chain      - The {@link FilterChain} being processed.
     * @param authResult - The {@link Authentication} result being processed.
     * @throws IOException The {@link IOException} thrown.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        Claims claims = new DefaultClaims();
        Map<String, Object> responseBody = new HashMap<>();

        // Extracts the Username from the Auth result.
        String username = ((UserDetails) authResult.getPrincipal()).getUsername();

        // Extracts the Authority level of the result to put into the Claim.
        List<String> authorities = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("authorities", authorities);

        // Generate the JWT token given the username and claim. Sets the Timelimit of the token with the given offset from the config.
        String token = JwtTokenService.generateToken(username, claims, demoApplicationConfig.getDefaultTokenTimeLimitInMilliseconds());
        // Update the response body.
        responseBody.put("Username", username);
        responseBody.put("token", token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // Marshall response to JSON.
        ObjectMapperInstance.INSTANCE.getObjectMapper().writeValue(response.getWriter(), responseBody);
    }
}
