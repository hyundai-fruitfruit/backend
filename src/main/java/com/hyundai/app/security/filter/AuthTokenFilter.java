package com.hyundai.app.security.filter;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import com.hyundai.app.member.enumType.Header;
import com.hyundai.app.security.AuthDetailsService;
import com.hyundai.app.security.AuthUserDetails;
import com.hyundai.app.security.jwt.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 황수영
 * @since 2024/02/12
 * access token 유효성 검증 필터
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtTokenGenerator jwtTokenGenerator;
    private final AuthDetailsService authUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            log.debug("AuthTokenFilter : request " + request);
            String accessToken = resolveToken(request);
            log.debug("AuthTokenFilter : accessToken " + accessToken);
            jwtTokenGenerator.isTokenValidate(accessToken);

            Authentication authentication = createAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.debug("AuthTokenFilter : accessToken 토큰이 유효하지 않습니다.");
            throw new AdventureOfHeendyException(ErrorCode.ACCESS_TOKEN_INVALID);
        }
        filterChain.doFilter(request, response);
    }

    public Authentication createAuthentication(String token) {
        String id = jwtTokenGenerator.getClaims(token).getSubject();
        log.debug("AuthTokenFilter : createAuthentication => id : " + id);
        AuthUserDetails authUserDetails = authUserDetailsService.loadUserByUsername(id);
        return new UsernamePasswordAuthenticationToken(
                authUserDetails,
                "",
                authUserDetails.getAuthorities());
    }


    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Header.AUTH.getValue());
        if (bearerToken != null && bearerToken.startsWith(Header.BEARER.getValue())) {
            return bearerToken.substring(Header.BEARER.getValue().length());
        }
        return null;
    }
}