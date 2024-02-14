package com.hyundai.app.security.jwt;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import com.hyundai.app.member.dto.LoginResDto;
import com.hyundai.app.security.AuthDetailsService;
import com.hyundai.app.security.AuthUserDetails;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import static com.hyundai.app.member.enumType.Header.BEARER;

/**
 * @author 황수영
 * @since 2024/02/12
 * JWT 발급하는 클래스
 */
@Log4j
@Component
@RequiredArgsConstructor
public class JwtTokenGenerator implements InitializingBean {

    @Value("${jwt.access-validity}")
    private long accessValidity;
    @Value("${jwt.refresh-validity}")
    private long refreshValidity;
    @Value("${jwt.secret}")
    private String jwtSecret;
    private final AuthDetailsService authDetailsService;

    @Override
    public void afterPropertiesSet() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = getClaims(accessToken);
        AuthUserDetails userDetails = authDetailsService.loadUserByUsername(claims.getSubject());
        log.debug("AuthTokenGenerator getAuthentication() userDetails : " + userDetails);

        return new UsernamePasswordAuthenticationToken(
                userDetails, accessToken, userDetails.getAuthorities());
    }

    public LoginResDto createLoginResDto(String id) {
        String accessToken = createJwtToken(id, accessValidity);
        String refreshToken = createJwtToken(id, refreshValidity);

        return LoginResDto.builder()
                .accessToken(BEARER.getValue() + accessToken)
                .refreshToken(BEARER.getValue() + refreshToken)
                .build();
    }

    public String createJwtToken(String id, long validity) {
        Date now = new Date();
        Claims claims = Jwts.claims()
                .setSubject(id);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setExpiration(new Date(now.getTime() + validity))
                .compact();
    }

    public Claims getClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public void isTokenValidate(String token) {
        try {
            Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new AdventureOfHeendyException(ErrorCode.ACCESS_TOKEN_INVALID);
        }
    }
}