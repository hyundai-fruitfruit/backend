package com.hyundai.app.member.mapper;

import com.hyundai.app.member.domain.Member;
import org.apache.ibatis.annotations.Param;

/**
 * @author 황수영
 * @since 2024/02/12
 * 회원 관련 mapper
 */
public interface MemberMapper {

    void saveMember(Member member);
    Member findById(String id);
    Member findByOauthId(String oauthId);
    void updateQrUrl(Member member);
    void updateDeviceToken(@Param("id") String id, @Param("token")String token);
}
