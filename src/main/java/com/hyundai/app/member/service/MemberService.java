package com.hyundai.app.member.service;

import com.hyundai.app.member.dto.LoginReqDto;
import com.hyundai.app.member.dto.LoginResDto;
import com.hyundai.app.member.dto.MemberResDto;
import com.hyundai.app.member.enumType.OauthType;

/**
 * @author 황수영
 * @since 2024/02/12
 * 회원 기능 서비스단
 */
public interface MemberService {

    MemberResDto getMemberInfo(int id);

    LoginResDto login(LoginReqDto loginReqDto);

    LoginResDto joinByOauthId(String email, OauthType oauthType);

    String generateQrCodeAndUploadToS3(Integer memberId);
}