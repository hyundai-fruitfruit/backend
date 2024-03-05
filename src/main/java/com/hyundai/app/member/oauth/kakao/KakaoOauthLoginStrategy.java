package com.hyundai.app.member.oauth.kakao;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.enumType.Header;
import com.hyundai.app.member.enumType.OauthType;
import com.hyundai.app.member.oauth.LoginStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author 황수영
 * @since 2024 /02/12
 * 카카오 OAuth 관련 클래스
 */
@Log4j
@Service
@RequiredArgsConstructor
public class KakaoOauthLoginStrategy implements LoginStrategy {

    @Value("${oauth.kakao.request-uri}")
    private String reqUri;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public OauthType oAuthType() {
        return OauthType.KAKAO;
    }

    /**
     * @author 황수영
     * @since 2024/02/12
     * OAuth로부터 사용자 정보 이메일 가져오기
     */
    @Override
    public String getEmail(LoginReqDto loginRequestDto) {
        KakaoResDto kakaoResDto = getMemberInfoByLoginToken(loginRequestDto.getLoginToken());
        return kakaoResDto.getEmail();
    }

    /**
     * @author 황수영
     * @since 2024/02/12
     * OAuth로부터 사용자 정보 요청
     */
    private KakaoResDto getMemberInfoByLoginToken(String accessToken) {
        log.info("getUserInfoByLoginToken() - accessToken : " + accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(Header.AUTH.getValue(), Header.BEARER.getValue() + accessToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        return getMemberInfoFromOAuth(request);
    }

    /**
     * @author 황수영
     * @since 2024/02/12
     * Kakao에서 받아온 사용자 정보에서 email 추출
     */
    private KakaoResDto getMemberInfoFromOAuth(HttpEntity<MultiValueMap<String, String>> request) {
        KakaoResDto kakaoResDto = null;
        try {
            kakaoResDto = restTemplate.postForObject(reqUri, request, KakaoResDto.class);
            log.debug("kakaoResDto : " + kakaoResDto);
            return kakaoResDto;
        } catch(HttpClientErrorException e) {
            log.error("HttpClientErrorException : " + e.getResponseBodyAsString());
        } catch(HttpServerErrorException e) {
            log.error("HttpServerErrorException : " + e.getResponseBodyAsString());
        } catch(Exception e) {
            log.error("Exception : " + e);
        }
        throw new AdventureOfHeendyException(ErrorCode.OAUTH_UNAUTHORIZED);
    }
}