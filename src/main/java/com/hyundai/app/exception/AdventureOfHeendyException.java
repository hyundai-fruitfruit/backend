package com.hyundai.app.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 황수영
 * @since 2024/02/12
 * '흰디의 모험' 서비스 자체 예외 정의
 */
@Getter
@RequiredArgsConstructor
public class AdventureOfHeendyException extends RuntimeException {
    private final ErrorCode errorCode;
}