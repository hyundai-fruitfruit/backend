package com.hyundai.app.security.handler;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 황수영
 * @since 2024/02/12
 * 인증되지 않은 사용자의 요청 에러 핸들러
 */
@Log4j
@Component
public class AuthTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.debug("AuthTokenAuthenticationEntryPoint : 인증되지 않은 사용자의 요청");

        throw new AdventureOfHeendyException(ErrorCode.UNAUTHORIZED_ACCESS);
    }
}