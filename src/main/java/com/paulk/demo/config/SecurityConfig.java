package com.paulk.demo.config;

import com.paulk.demo.dao.UserOperationService;
import com.paulk.demo.security.JwtAuthenticationFilter;
import com.paulk.demo.security.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * A {@link Configuration} bean for all security related configuration.
 *
 * Enabled PreAuthorize annotation.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected UserOperationService userOperationService;

    @Autowired
    protected DemoApplicationConfig demoApplicationConfig;

    /**
     * Get a {@link PasswordEncoder} using {@link BCryptPasswordEncoder}.
     *
     * @return A {@link PasswordEncoder}.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an accessor for accessing the {@link AuthenticationManager} via a {@link Bean}.
     *
     * @return The {@link AuthenticationManager} for the {@link WebSecurityConfigurerAdapter}.
     * @throws Exception Thrown if an error accessing the {@link AuthenticationManager}.
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configure the {@link AuthenticationManagerBuilder} to know which {@link Service} implements {@link UserDetailsService}.
     *
     * @param authenticationManagerBuilder - The {@link AuthenticationManagerBuilder} being processed.
     * @throws Exception An exception thrown when configuring the {@link AuthenticationManagerBuilder}.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userOperationService)
                .passwordEncoder(getPasswordEncoder());
    }

    /**
     * Configure the {@link HttpSecurity} with the {@link JwtAuthorizationFilter} and {@link JwtAuthenticationFilter} so it can intercept and execute at runtime.
     *
     * @param httpSecurity - The {@link HttpSecurity} class being overridden..
     * @throws Exception An exception thrown when configuring {@link HttpSecurity}.
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .ignoringAntMatchers("/**")
                .and()
                .addFilter(new JwtAuthenticationFilter(this, demoApplicationConfig))
                .addFilter(new JwtAuthorizationFilter(authenticationManagerBean()));
    }
}
