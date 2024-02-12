package com.hyundai.app.member.mapper;

import com.hyundai.app.member.domain.Member;

/**
 * @author 황수영
 * @since 2024/02/12
 * 회원 관련 mapper
 */
public interface MemberMapper {

    void saveMember(Member member);
    Member findById(int id);
    Member findByOauthId(String oAuthId);
}
