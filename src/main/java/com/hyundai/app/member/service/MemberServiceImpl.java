package com.hyundai.app.member.service;

import com.hyundai.app.member.domain.Member;
import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.dto.LoginResDto;
import com.hyundai.app.member.dto.MemberResDto;
import com.hyundai.app.member.enumType.Header;
import com.hyundai.app.member.enumType.Nickname;
import com.hyundai.app.member.enumType.OauthType;
import com.hyundai.app.member.enumType.Role;
import com.hyundai.app.member.mapper.MemberMapper;
import com.hyundai.app.member.oauth.kakao.KakaoOauthClient;
import com.hyundai.app.security.jwt.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

/**
 * @author 황수영
 * @since 2024/02/13
 * (설명)
 */
@Log4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Value("${jwt.access-validity}")
    private long accessValidity;

    private final MemberMapper memberMapper;
    private final KakaoOauthClient oAuthClient;
    private final JwtTokenGenerator authTokenGenerator;
    private final AwsS3Config awsS3Config;
    private final MemberQrService memberQrService;

    public MemberResDto getMemberInfo(String id) {
        Member member = memberMapper.findById(id);
        return MemberResDto.of(member);
    }

    public LoginResDto login(LoginReqDto loginReqDto) {
        String email = oAuthClient.getEmail(loginReqDto);
        OauthType oauthType = OauthType.valueOf(loginReqDto.getOauthType().toUpperCase());
        String oauthId = oauthType.createOauthIdWithEmail(email);
        log.debug("로그인 OauthId : " + oauthId);

        Member findMember = memberMapper.findByOauthId(oauthId);
        if (findMember != null) {
            return getUpdatedToken(findMember);
        }
        return joinByOauthId(email, oauthType);
    }

    private String updateAccessToken(Member member) {
        String memberId = String.valueOf(member.getId());
        return authTokenGenerator.createJwtToken(memberId, accessValidity);
    }

    private LoginResDto getUpdatedToken(Member member) {
        String newAccessToken = updateAccessToken(member);
        String refreshToken = member.getRefreshToken();

        return LoginResDto.builder()
                .accessToken(Header.BEARER.getValue() + newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public LoginResDto joinByOauthId(String email, OauthType oauthType) {
        String oauthId = oauthType.createOauthIdWithEmail(email);
        LoginResDto loginResDto = authTokenGenerator.createLoginResDto(oauthId);
        String memberId = UUID.randomUUID().toString();
        String qrUrl = generateQrCodeAndUploadToS3(memberId);

        Member member = Member.builder()
                .id(memberId)
                .email(email)
                .nickname(Nickname.getRandomNickname())
                .role(Role.ROLE_MEMBER)
                .oauthId(oauthId)
                .refreshToken(loginResDto.getRefreshToken())
                .qrUrl(qrUrl)
                .build();
        log.debug("joinByEmail member" + member.toString());
        memberMapper.saveMember(member);

        Member savedMember = memberMapper.findByOauthId(oauthId);
        log.debug("joinByEmail savedMember" + savedMember);

        return loginResDto;
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
