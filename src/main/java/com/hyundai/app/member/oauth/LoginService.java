package com.hyundai.app.member.oauth;

import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.oauth.kakao.KakaoOauthLoginStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/03/04
 * LoginService
 */
@Log4j
@Component
@RequiredArgsConstructor
public class LoginService {

    private final List<LoginStrategy> loginStrategies;
    private final KakaoOauthLoginStrategy kakaoOauthLoginStrategy;

    public LoginStrategy getOAuthLoginClient(LoginReqDto loginRequestDto) {

        return this.loginStrategies.stream()
                .filter(s -> s.oAuthType().toString().equals(loginRequestDto.getOauthType()))
                .findFirst()
                .orElse(kakaoOauthLoginStrategy);
    }
}