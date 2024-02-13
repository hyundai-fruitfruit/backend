package com.hyundai.app.member.dto;

import com.hyundai.app.member.domain.Member;
import com.hyundai.app.member.enumType.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 황수영
 * @since 2024/02/13
 * 회원 정보 조회 Res DTO
 */
@Getter
@NoArgsConstructor
public class MemberResDto {
    private String email;
    private Role role;
    private String nickname;
    private String oauthId;

    public static MemberResDto of(Member member) {
        MemberResDto memberResDto = new MemberResDto();
        memberResDto.email = member.getEmail();
        memberResDto.nickname = member.getNickname();
        memberResDto.role = member.getRole();
        memberResDto.oauthId = member.getOauthId();
        return memberResDto;
    }
}