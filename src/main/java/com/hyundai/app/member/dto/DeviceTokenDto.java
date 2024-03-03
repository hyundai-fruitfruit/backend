package com.hyundai.app.member.dto;

import lombok.Getter;

/**
 * @author 황수영
 * @since 2024/03/02
 * FCM 디바이스 토큰 값 업데이트
 */
@Getter
public class DeviceTokenDto {
    private String platform; // FCM
    private String token;
}