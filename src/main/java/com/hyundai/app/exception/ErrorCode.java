package com.hyundai.app.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

/**
 * @author 황수영
 * @since 2024/02/12
 * '흰디의 모험' 자체 에러 정의
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Access Token 파싱
    ACCESS_TOKEN_INVALID(UNAUTHORIZED,  "Access Token이 유효하지 않습니다."),
    MEMBER_LOGOUT(UNAUTHORIZED,  "로그아웃된 사용자입니다."),
    // Refresh Token
    REFRESH_TOKEN_NOT_EXIST(UNAUTHORIZED, "해당 Refresh Token을 가진 사용자가 없습니다."),
    REFRESH_TOKEN_INVALID(UNAUTHORIZED,  "Refresh Token이 유효하지 않습니다."),
    // OAuth
    OAUTH_UNAUTHORIZED(UNAUTHORIZED, "OAuth 인증에 실패했습니다."),
    // 인증 및 인가
    UNAUTHORIZED_ACCESS(UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    FORBIDDEN_ACCESS(FORBIDDEN, "인가되지 않은 접근입니다."),

    // 리뷰
    REVIEW_SCORE_INVALID(BAD_REQUEST, "별점은 1~5점까지의 정수이어야 합니다."),
    REVIEW_CONTENT_INVALID(BAD_REQUEST, "리뷰 내용은 최소 5자 이상이어야합니다."),

    // 아이디값
    STORE_ID_INVALID(BAD_REQUEST, "해당하는 매장 id가 없습니다."),
    HASHTAG_ID_INVALID(BAD_REQUEST, "해당하는 해시태그 id가 없습니다."),
    MEMBER_ID_INVALID(BAD_REQUEST, "해당하는 회원 id가 없습니다."),

    // 500 에러
    SERVER_UNAVAILABLE(SERVICE_UNAVAILABLE, "서버에 오류가 발생하였습니다."),

    // 400 에러
    INVALID_PARAMETER(BAD_REQUEST, "입력값이 형식에 맞지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}