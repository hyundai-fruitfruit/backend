package com.hyundai.app.security;

import com.hyundai.app.member.mapper.MemberMapper;
import com.hyundai.app.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 황수영
 * @since 2024/02/12
 * 시큐리티의 UserDetailsService 구현, DB에서 유저 존재 여부 확인
 */
@Log4j
@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public AuthUserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberMapper.findById(id);
        log.debug("loadUserByUsername() => member : " + id);
        return new AuthUserDetails(member);
    }
}