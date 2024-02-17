package com.hyundai.app.friend.mapper;

import com.hyundai.app.friend.dto.FriendDto;
import com.hyundai.app.friend.enumType.GameStatus;

/**
 * @author 엄상은
 * @since 2024/02/14
 * 게임 관련 MAPPER
 */
public interface GameMapper {
    GameStatus findGameStatus(FriendDto friendDto);
}
