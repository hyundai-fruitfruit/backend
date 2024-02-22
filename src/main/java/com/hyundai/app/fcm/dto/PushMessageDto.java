package com.hyundai.app.fcm.dto;

import com.hyundai.app.fcm.PushType;
import lombok.Getter;

/**
 * @author 황수영
 * @since 2024/02/20
 * 푸시 알림용 DTO
 */

@Getter
public class PushMessageDto {
    private String title;
    private String content;
    private String image;

    public static PushMessageDto of(PushType pushType) {
        PushMessageDto pushMessageDto = new PushMessageDto();
        pushMessageDto.content = pushType.getContent();
        pushMessageDto.title = pushType.getTitle();
        pushMessageDto.image = pushType.getImage();
        return pushMessageDto;
    }
}