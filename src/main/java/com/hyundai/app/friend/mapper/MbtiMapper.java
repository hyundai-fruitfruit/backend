package com.hyundai.app.friend.mapper;

import com.hyundai.app.friend.dto.FriendDto;
import com.hyundai.app.friend.dto.MbtiSaveReqDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 엄상은
 * @since 2024/02/13
 * MBTI 관련 MAPPER
 */
@Mapper
public interface MbtiMapper {
    String findIdByMbtiScore(MbtiSaveReqDto mbtiSaveReqDto);
    String findMbtiByFriend(FriendDto friendDto);
}
