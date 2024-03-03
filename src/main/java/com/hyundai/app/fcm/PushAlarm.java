package com.hyundai.app.fcm;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/03/02
 * 푸시 알림 도메인 (이벤트별로 메시지 설정용)
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PushAlarm {
    private String title;
    private String content;
    private String image;
}
