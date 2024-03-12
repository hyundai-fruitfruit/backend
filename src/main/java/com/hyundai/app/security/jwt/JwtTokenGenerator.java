package com.hyundai.app.security.jwt;

import com.hyundai.app.member.dto.LoginResDto;
import com.hyundai.app.member.enumType.Header;
import com.hyundai.app.security.AuthDetailsService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
    
    /**
     * @author 황수영
     * @since 2024/02/14
     * 로그인 Res Dto 생성
     */
    public LoginResDto createLoginResDto(String id) {
        String accessToken = createJwtToken(id, accessValidity);
        String refreshToken = createJwtToken(id, refreshValidity);

        return LoginResDto.builder()
                .accessToken(BEARER.getValue() + accessToken)
                .refreshToken(BEARER.getValue() + refreshToken)
                .build();
    }

    /**
     * @author 황수영
     * @since 2024/02/14
     * JWT 생성
     */
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

    /**
     * @author 황수영
     * @since 2024/02/14
     * JWT 파싱
     */
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
    
    /**
     * @author 황수영
     * @since 2024/02/14
     * 토큰 유효성 검증
     */
    public boolean isTokenValidate(String token) {
        try {
            Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("isTokenValidate() 토큰 파싱 시, 에러 발생 ");
        }
        return false;
    }

    /**
     * @author 황수영
     * @since 2024/02/14
     * 토큰 유효성 검증
     */
    public Long getExpirationTime(String accessToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(accessToken)
                .getBody();

        long expirationTimestamp = claims.getExpiration().getTime();
        log.debug("JWT Expiration Timestamp: " + expirationTimestamp);

        long now = new Date().getTime();
        return expirationTimestamp - now;
    }


    /**
     * @author 황수영
     * @since 2024/02/14
     * JWT 토큰 파싱
     */
    public String resolveToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(com.hyundai.app.member.enumType.Header.BEARER.getValue())) {
            return bearerToken.substring(Header.BEARER.getValue().length());
        }
        return null;
    }
}