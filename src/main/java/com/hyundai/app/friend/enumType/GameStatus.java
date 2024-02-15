package com.hyundai.app.friend.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author 엄상은
 * @since 2024/02/14
 * 게임 상태 ENUM 객체
 */
@Getter
@ToString
@AllArgsConstructor
public enum GameStatus {
    NO_GAME_TODAY("1번 가능"),
    FRIEND_REQUESTED("게임 수락하기"),
    USER_REQUESTED("기다리는 중"),
    GAME_PLAYED("내일 다시 가능");

    private final String description;
}
