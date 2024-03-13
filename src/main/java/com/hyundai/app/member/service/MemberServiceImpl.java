package com.hyundai.app.member.service;

import com.hyundai.app.config.AwsS3Config;
import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import com.hyundai.app.member.domain.Member;
import com.hyundai.app.member.dto.DeviceTokenDto;
import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.dto.LoginResDto;
import com.hyundai.app.member.dto.MemberResDto;
import com.hyundai.app.member.enumType.Header;
import com.hyundai.app.member.enumType.Nickname;
import com.hyundai.app.member.enumType.OauthType;
import com.hyundai.app.member.enumType.Role;
import com.hyundai.app.member.mapper.MemberMapper;
import com.hyundai.app.member.oauth.LoginService;
import com.hyundai.app.member.oauth.LoginStrategy;
import com.hyundai.app.security.jwt.JwtTokenGenerator;
import com.hyundai.app.util.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

/**
 * @author 황수영
 * @since 2024/02/13
 * 회원 관련 서비스단
 */
/**
 * @author 엄상은
 * @since 2024/02/26
 * 회원 QR 코드 관련 서비스단
 */
@Log4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Value("${jwt.access-validity}")
    private long accessValidity;
    private final MemberMapper memberMapper;
    private final LoginService loginService;
    private final JwtTokenGenerator authTokenGenerator;
    private final AwsS3Config awsS3Config;
    private final MemberQrService memberQrService;
    private final RedisService redisUtil;
    private static final String LOGOUT_BLACKLIST = "logout";


    /**
     * @author 황수영
     * @since 2024/02/13
     * 회원가입/로그인 기능
     */
    public LoginResDto login(LoginReqDto loginReqDto) {
        LoginStrategy loginStrategy = loginService.getOAuthLoginClient(loginReqDto);
        String email = loginStrategy.getEmail(loginReqDto);
        OauthType oauthType = loginStrategy.oAuthType();
        String oauthId = oauthType.createOauthIdWithEmail(email);
        log.debug("로그인 OauthId : " + oauthId);

        Member findMember = memberMapper.findByOauthId(oauthId);
        if (findMember != null) {
            return getUpdatedToken(findMember);
        }
        return joinByOauthId(email, oauthType);
    }

    /**
     * @author 황수영
     * @since 2024/02/13
     * access token 재발급
     */
    private String updateAccessToken(Member member) {
        String memberId = String.valueOf(member.getId());
        return authTokenGenerator.createJwtToken(memberId, accessValidity);
    }

    /**
     * @author 황수영
     * @since 2024/02/13
     * token 갱신
     */
    private LoginResDto getUpdatedToken(Member member) {
        String newAccessToken = updateAccessToken(member);
        String refreshToken = member.getRefreshToken();

        return LoginResDto.builder()
                .accessToken(Header.BEARER.getValue() + newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * @author 황수영
     * @since 2024/02/13mvn clean package
     * oauth id값으로 회원가입
     */
    public LoginResDto joinByOauthId(String email, OauthType oauthType) {
        String memberId = UUID.randomUUID().toString();
        LoginResDto loginResDto = authTokenGenerator.createLoginResDto(memberId);
        String qrUrl = generateQrCodeAndUploadToS3(memberId);

        Member member = Member.builder()
                .id(memberId)
                .email(email)
                .nickname(Nickname.getRandomNickname())
                .role(Role.ROLE_MEMBER)
                .oauthId(oauthType.createOauthIdWithEmail(email))
                .refreshToken(loginResDto.getRefreshToken())
                .qrUrl(qrUrl)
                .build();
        log.debug("joinByEmail member" + member.toString());
        memberMapper.saveMember(member);
        return loginResDto;
    }
    
    /**
     * @author 황수영
     * @since 2024/02/13
     * 회원 정보 조회
     */
    public MemberResDto getMemberInfo(String id) {
        log.debug("회원 정보 조회 : " + id);
        Member member = memberMapper.findById(id);
        if (member == null) {
            log.error("회원 id가 존재하지 않습니다. : " + id);
            throw new AdventureOfHeendyException(ErrorCode.MEMBER_NOT_EXIST);
        }
        return MemberResDto.of(member);
    }

    /**
     * @author 황수영
     * @since 2024/02/13
     * FCM 디바이스 토큰 업데이트
     */
    @Override
    public void updateDeviceToken(String memberId, DeviceTokenDto deviceTokenDto) {
        log.debug("FCM 디바이스 토큰 업데이트 : " + memberId);
        Member member = memberMapper.findById(memberId);
        if (member == null) {
            log.error("회원 id가 존재하지 않습니다. : " + memberId);
            throw new AdventureOfHeendyException(ErrorCode.MEMBER_NOT_EXIST);
        }
        memberMapper.updateDeviceToken(memberId, deviceTokenDto.getToken());
    }

    /**
     * @author 황수영
     * @since 2024/02/13
     * 로그아웃
     */
    public void logout(LoginReqDto loginReqDto) {
        String bearerToken = loginReqDto.getLoginToken();
        String accessToken = authTokenGenerator.resolveToken(bearerToken);
        Long expirationTime = authTokenGenerator.getExpirationTime(accessToken);

        redisUtil.setBlackList(accessToken, LOGOUT_BLACKLIST, expirationTime);
    }

    /**
     * @author 엄상은
     * @since 2024/02/26
     * 큐알코드 생성해 S3에 업로드
     */
    public String generateQrCodeAndUploadToS3(String memberId) {
        File qrFile = memberQrService.generateQrCode(memberId);
        String url = awsS3Config.uploadPngFile(qrFile.getName(), qrFile);
        log.debug("큐알코드 생성 및 업로드 완료 : " + url);
        return url;
    }

    /**
     * @author 엄상은
     * @since 2024/02/26
     * 큐알코드 조회
     */
    public String findQrUrl(String memberId) {
        Member member = memberMapper.findById(memberId);
        if (member != null) {
            return member.getQrUrl();
        }
        return null;
    }
}
