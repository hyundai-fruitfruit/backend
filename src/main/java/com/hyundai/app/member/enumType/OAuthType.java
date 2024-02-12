package com.hyundai.app.member.enumType;

import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author 황수영
 * @since 2024/02/12
 * OAuth 타입
 */
@Getter
public enum OAuthType {
    KAKAO, GOOGLE;

    public static OAuthType valueOfUpperCase(String value) {
        return Arrays.stream(values())
                .filter(oauth -> oauth.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당하는 OAuth 타입이 존재하지 않습니다."));
    }
}