package com.hyundai.app.security;

import com.hyundai.app.member.mapper.MemberMapper;
import com.hyundai.app.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

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
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberMapper.findById(Integer.parseInt(id));
        log.debug("loadUserByUsername() => member : " + member);
        return createUserDetails(member);
    }

    private UserDetails createUserDetails(Member member) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(member.getRole().toString()));

        return new User(
                String.valueOf(member.getId()),
                String.valueOf(member.getId()),
                grantedAuthorities);
    }
}