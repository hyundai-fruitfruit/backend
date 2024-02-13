package com.hyundai.app.security.filter;

import com.hyundai.app.security.jwt.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author 황수영
 * @since 2024/02/12
 * AuthDetailsService
 */
@Log4j
@Component
@RequiredArgsConstructor
public class AuthTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenGenerator jwtTokenGenerator;

    @Override
    public void configure(HttpSecurity httpSecurity) {
        AuthTokenFilter customFilter = new AuthTokenFilter(jwtTokenGenerator);
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}