package com.hyundai.app.fcm.dto;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/02/20
 * 유저 디바이스 FCM 푸시 알림용 Request DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PushReqDto {
    private String deviceToken;
    private int delayedSeconds;

    public static PushReqDto of(String deviceToken, int time) {
        return new PushReqDto(deviceToken, time);
    }
}