package com.hyundai.app.member.oauth.google;

import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.enumType.OauthType;
import com.hyundai.app.member.oauth.LoginStrategy;

/**
 * @author 황수영
 * @since 2024/03/04
 * 구글 로그인 이후 추가
 */
public class GoogleOauthLoginStrategy implements LoginStrategy {
    @Override
    public OauthType oAuthType() {
        return OauthType.GOOGLE;
    }

    @Override
    public String getEmail(LoginReqDto loginRequestDto) {
        return "google@gmail.com";
    }
}