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

    public MemberResDto getMemberInfo(int id) {
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

        Member member = Member.builder()
                .email(email)
                .nickname(Nickname.getRandomNickname())
                .role(Role.ROLE_MEMBER)
                .oauthId(oauthId)
                .refreshToken(loginResDto.getRefreshToken())
                .build();
        log.debug("joinByEmail member" + member.toString());
        memberMapper.saveMember(member);

        Member savedMember = memberMapper.findByOauthId(oauthId);
        log.debug("joinByEmail savedMember" + savedMember);
        return loginResDto;
    }
}