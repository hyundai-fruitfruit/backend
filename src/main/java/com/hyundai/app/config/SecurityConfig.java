package com.hyundai.app.config;

import com.hyundai.app.security.filter.AuthTokenFilterConfigurer;
import com.hyundai.app.security.handler.AuthTokenAccessDeniedHandler;
import com.hyundai.app.security.handler.AuthTokenAuthenticationEntryPoint;
import com.hyundai.app.security.jwt.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 황수영
 * @since 2024/02/13
 * Security 설정
 */
@Log4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenGenerator authTokenGenerator;
    private final AuthTokenAccessDeniedHandler authTokenAccessDeniedHandler;
    private final AuthTokenAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/", "/swagger-ui/**", "/api/v1/auth/**");
    }
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                    .csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .headers()
                    .httpStrictTransportSecurity().disable()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                    .exceptionHandling()
                    .accessDeniedHandler(authTokenAccessDeniedHandler)
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/v1/auth/**").permitAll()
                    .antMatchers("/api/v1/member/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .apply(new AuthTokenFilterConfigurer(authTokenGenerator));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}