package com.hyundai.app.member.controller;

import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.dto.LoginResDto;
import com.hyundai.app.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    @Qualifier("memberServiceImpl")
    private MemberService memberService;

    @PostMapping("/login")
    @ApiOperation("회원가입/로그인 API")
    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
        log.debug("로그인 시도 => 토큰 : " + loginReqDto.getLoginToken());
        LoginResDto loginResDto = memberService.login(loginReqDto);
        return new ResponseEntity<>(loginResDto, HttpStatus.ACCEPTED);
    }
}