package com.hyundai.app.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author 황수영
 * @since 2024/02/28
 * 예외 응답 반환하는 핸들러
 */
@Log4j
@RestControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    /**
     * @author 황수영
     * @since 2024/02/28
     * 자체 예외 응답 처리
     */
    @ExceptionHandler(AdventureOfHeendyException.class)
    public ResponseEntity<Object> handleCustomException(AdventureOfHeendyException e) {
        return handleExceptionInternal(e);
    }

    /**
     * @author 황수영
     * @since 2024/02/28
     * 자체 예외 이외의 모든 예외 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e) {
        return handleExceptionAll(e);
    }

    /**
     * @author 황수영
     * @since 2024/02/28
     * 자체 예외 ResponseEntity 생성
     */
    private ResponseEntity<Object> handleExceptionInternal(AdventureOfHeendyException e) {
        log.error("자체 예외 발생 : " + e);

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.builder()
                        .errorMessage(e.getErrorCode().getMessage())
                        .errorType(String.valueOf(e.getErrorCode()))
                        .httpStatus(e.getErrorCode().getHttpStatus())
                        .build());
    }

    /**
     * @author 황수영
     * @since 2024/02/28
     * 자체 예외 이외의 모든 ResponseEntity 생성
     */
    private ResponseEntity<Object> handleExceptionAll(Exception e) {
        log.error("예외 발생 : " + e);

        return ResponseEntity.status(ErrorCode.SERVER_UNAVAILABLE.getHttpStatus())
                .body(ErrorResponse.builder()
                        .errorMessage(e.getMessage())
                        .errorType(e.toString())
                        .httpStatus(ErrorCode.SERVER_UNAVAILABLE.getHttpStatus())
                        .build());
    }
}