package com.hyundai.app.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 엄상은
 * @since 2024/02/14
 * 친구 관계 DTO
 */
@Getter
@AllArgsConstructor
public class FriendDto {
    private String memberId;
    private String friendId;
}
