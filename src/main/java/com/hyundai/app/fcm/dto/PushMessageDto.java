package com.hyundai.app.fcm.dto;

import com.hyundai.app.fcm.PushType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 황수영
 * @since 2024/02/20
 * 푸시 알림용 DTO
 */

@Getter
@AllArgsConstructor
public class PushMessageDto {
    private String title;
    private String content;
    private String image;

    public static PushMessageDto from(PushType pushType) {
        return new PushMessageDto(pushType.getTitle(), pushType.getContent(), pushType.getImage());
    }

    public static PushMessageDto of(String title, String content, String image) {
        return new PushMessageDto(title, content, image);
    }
}