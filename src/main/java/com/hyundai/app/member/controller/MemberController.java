package com.hyundai.app.member.controller;

import com.hyundai.app.member.dto.MemberResDto;
import com.hyundai.app.member.service.MemberService;
import com.hyundai.app.security.methodparam.MemberId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 황수영
 * @since 2024/02/14
 * 회원 관련 기능 컨트롤러
 */
@Log4j
@Api("회원 관련 API")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    @Qualifier("memberServiceImpl")
    private MemberService memberService;

    @GetMapping
    @ApiOperation("회원 정보 조회 API")
    public ResponseEntity<MemberResDto> login(@ApiIgnore @MemberId Integer memberId) {
        log.debug("회원 정보 조회 : " + memberId);
        MemberResDto memberResDto = memberService.getMemberInfo(memberId);
        return new ResponseEntity<>(memberResDto, HttpStatus.ACCEPTED);
    }
}