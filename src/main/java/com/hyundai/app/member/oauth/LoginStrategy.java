package com.hyundai.app.member.oauth;

import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.enumType.OauthType;

/**
 * @author 황수영
 * @since 2024/03/04
 * OAuth 로그인 인터페이스
 */
public interface LoginStrategy {
    OauthType oAuthType();
    String getEmail(LoginReqDto loginRequestDto);
}
