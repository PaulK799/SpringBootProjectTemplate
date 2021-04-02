package com.paulk.demo.security;

import com.paulk.demo.utils.ObjectMapperInstance;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Extend the {@link JwtAuthorizationFilter} to Authorize the token submitted.
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    /**
     * Constructor for the {@link JwtAuthorizationFilter}
     *
     * @param authenticationManager - The {@link AuthenticationManager} being processed.
     */
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * Override the doFilterInternal to authorize the JWT token provided.
     *
     * @param request  - The {@link HttpServletRequest} being processed.
     * @param response - The {@link HttpServletResponse} being processed.
     * @param chain    - The {@link FilterChain} being processed.
     * @throws IOException      The {@link IOException} thrown.
     * @throws ServletException The {@link ServletException} thrown.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");

        // Check Conditions.
        // 1. Check not null.
        // 2. Check Token starts with "Bearer X".
        // Otherwise Skip authorization.
        if (token == null || !token.startsWith("Bearer ")) {
            SecurityContextHolder.getContext().setAuthentication(null);
            chain.doFilter(request, response);
            return;
        }

        // Check Conditions.
        // 1. Validate token is valid.
        try {
            JwtTokenService.verifyToken(token);
        } catch (JwtException e) {
            // Return 401 Unauthorized Error Response.
            SecurityContextHolder.getContext().setAuthentication(null);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ObjectMapperInstance.INSTANCE.getObjectMapper().writeValue(response.getWriter(), e.getMessage());
            return;
        }

        // Get the Claims for the Token provided.
        Claims claims = JwtTokenService.getClaimsFromToken(token);
        if (claims != null) {
            // Parse the roles
            List<String> authorities = Optional.ofNullable(claims.get("authorities", ArrayList.class))
                    .orElseGet(ArrayList::new);

            List<SimpleGrantedAuthority> roleList = new ArrayList<>();
            if (!authorities.isEmpty()) {
                roleList = authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }

            // Get the username and check if set.
            String username = claims.getSubject();
            if (username != null) {
                // Add Authorized token to Security context and continue.
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, roleList));
                chain.doFilter(request, response);
            } else {
                // Return 401 Unauthorized Error Response.
                SecurityContextHolder.getContext().setAuthentication(null);
                response.setStatus(401);
            }
        }
    }
}
