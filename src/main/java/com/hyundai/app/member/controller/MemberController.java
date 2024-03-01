package com.hyundai.app.member.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.member.dto.MemberResDto;
import com.hyundai.app.member.service.MemberService;
import com.hyundai.app.security.methodparam.MemberId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    @Qualifier("memberServiceImpl")
    private MemberService memberService;

    /**
     * @author 황수영
     * @since 2024/02/14
     * 회원 조회 API
     */
    @GetMapping
    @ApiOperation("회원 정보 조회 API")
    public ResponseEntity<MemberResDto> login(@ApiIgnore @MemberId String memberId) {
        log.debug("회원 정보 조회 : " + memberId);
        MemberResDto memberResDto = memberService.getMemberInfo(memberId);
        return new ResponseEntity<>(memberResDto, HttpStatus.ACCEPTED);
    }

    /**
     * @author 엄상은
     * @since 2024/02/26
     * QR 생성 테스트 API
     */
    @PostMapping("/qr")
    @ApiOperation("QR 생성 테스트용 API")
    public AdventureOfHeendyResponse<String> generateQr(@ApiIgnore @MemberId String memberId) {
        return AdventureOfHeendyResponse.success("큐알 생성 성공", memberService.generateQrCodeAndUploadToS3(memberId));
    }

    /**
     * @author 엄상은
     * @since 2024/02/26
     * QR 조회 API
     */
    @GetMapping("/qr")
    @ApiOperation("QR 조회 API")
    public AdventureOfHeendyResponse<String> findQr(@ApiIgnore @MemberId String memberId) {
        return AdventureOfHeendyResponse.success("QR코드 조회를 성공하였습니다", memberService.findQrUrl(memberId));
    }
}
