package com.hyundai.app.member.oauth;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.oauth.kakao.KakaoOauthLoginStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.hyundai.app.exception.ErrorCode.OAUTH_INVALID;

/**
 * @author 황수영
 * @since 2024/03/04
 * LoginService
 */
@Component
@RequiredArgsConstructor
public class LoginService {

    private final List<LoginStrategy> loginStrategies;

    public LoginStrategy getOAuthLoginClient(LoginReqDto loginRequestDto) {
        String oAuthType = loginRequestDto.getOauthType().toUpperCase();

        return this.loginStrategies.stream()
                .filter(s-> s.oAuthType().toString().equals(oAuthType))
                .findFirst()
                .orElse(new KakaoOauthLoginStrategy());
                // .orElseThrow(() -> new AdventureOfHeendyException(OAUTH_INVALID));
    }
}