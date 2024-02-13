package com.hyundai.app.member.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 황수영
 * @since 2024/02/12
 * OAuth 타입
 */
@Getter
@AllArgsConstructor
public enum OauthType {
    KAKAO("KAKAO-"), GOOGLE("GOOGLE-");

    private final String oauthIdHeader;

    public String createOauthIdWithEmail(String email) {
        return this.getOauthIdHeader() + email;
    }
}