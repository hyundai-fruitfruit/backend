package com.hyundai.app.member.dto;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/02/12
 * 로그인 후 Res DTO
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto {

    private String accessToken;

    private String refreshToken;
}