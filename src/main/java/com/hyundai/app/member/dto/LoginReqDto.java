package com.hyundai.app.member.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 황수영
 * @since 2024/02/12
 * 프론트에서의 백으로의 로그인 Req DTO
 */
@Getter
@Setter
public class LoginReqDto {
    private String oauthType; // KAKAO
    private String loginToken; // OAuth의 acceess token
}