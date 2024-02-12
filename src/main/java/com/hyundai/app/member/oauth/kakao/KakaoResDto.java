package com.hyundai.app.member.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author 황수영
 * @since 2024/02/12
 * 카카오 OAuth에서 사용자 정보 요청 후 Res DTO
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoResDto {

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoAccount {
        private KakaoProfile profile;
        private String email;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoProfile {
        private String nickname;
    }

    public String getEmail() {
        return kakaoAccount.email;
    }
}
