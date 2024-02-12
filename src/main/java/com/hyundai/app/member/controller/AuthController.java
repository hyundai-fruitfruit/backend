package com.hyundai.app.member.controller;

import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.dto.LoginResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author 황수영
 * @since 2024/02/12
 * 인증/인가용 컨트롤러
 */
@Log4j
@Api("인증/인가용 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    // @Qualifier("MemberServiceImpl")
    private final com.hyundai.app.member.service.MemberServiceImpl memberServiceImpl;

    @PostMapping("/login")
    @ApiOperation("회원가입/로그인 API")
    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
        log.debug("로그인 시도 => 토큰 : " + loginReqDto.getLoginToken());
        LoginResDto loginResDto = memberServiceImpl.login(loginReqDto);
        return new ResponseEntity<>(loginResDto, HttpStatus.ACCEPTED);

    }
}