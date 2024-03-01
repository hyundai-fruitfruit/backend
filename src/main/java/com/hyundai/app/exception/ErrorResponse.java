package com.hyundai.app.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author 황수영
 * @since 2024/02/28
 * 예외 응답 형식
 */
@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final String errorMessage;
    private final String errorType;
    private final HttpStatus httpStatus;
}