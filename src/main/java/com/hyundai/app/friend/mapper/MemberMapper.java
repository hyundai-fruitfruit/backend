package com.hyundai.app.friend.mapper;

import com.hyundai.app.friend.domain.Member;

/**
 * @author 엄상은
 * @since 2024/02/13
 * 회원 관련 MAPPER
 */
public interface MemberMapper {
    Member findById(int memberId);
}
