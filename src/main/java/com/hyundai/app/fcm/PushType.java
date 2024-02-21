package com.hyundai.app.fcm;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.hyundai.app.fcm.dto.PushMessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 황수영
 * @since 2024/02/20
 * 푸시 알림 타입 및 메시지 형식 생성 기능
 */
@Getter
@AllArgsConstructor
public enum PushType {
    RANDOM_SPOT("흰디의 모험에 온 걸 환영해🎉",
            "나는 대장 흰디야! 반가워",
            "https://avatars.githubusercontent.com/u/158237286?s=400&u=db03152b8b64ca04183e918814f02316a5e8c4d9&v=4"),
    WELCOME("'흰디의 모험' 랜덤 스팟이 열렸어🎁",
            "랜덤 스팟에서의 이벤트를 확인해봐",
            "https://avatars.githubusercontent.com/u/158237286?s=400&u=db03152b8b64ca04183e918814f02316a5e8c4d9&v=4");

    private final String title;
    private final String content;
    private final String image;
    
    /**
     * @author 황수영
     * @since 2024/02/20
     * Notification 생성
     */
    public static Notification createNotification(PushType pushType) {
        PushMessageDto pushMessageDto = PushMessageDto.of(pushType);

        return Notification.builder()
                .setTitle(pushMessageDto.getTitle())
                .setBody(pushMessageDto.getContent())
                .setImage(pushMessageDto.getImage())
                .build();
    }

    /**
     * @author 황수영
     * @since 2024/02/20
     * Message 생성 - 이후 필요시 해당 메소드를 통해 유저에게 전달할 데이터 추가 가능
     */
    public static Message createMessage(Notification notification, String deviceToken) {
        return Message.builder()
                .setToken(deviceToken)
                .setNotification(notification)
                .build();
    }
}