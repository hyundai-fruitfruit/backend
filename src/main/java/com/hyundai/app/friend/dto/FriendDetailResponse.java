package com.hyundai.app.friend.dto;

import com.hyundai.app.friend.enumType.GameStatus;
import lombok.*;

/**
 * @author 엄상은
 * @since 2024/02/13
 * 친구 정보 조회 시 응답 객체
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendDetailResponse {
    private int id;
    private String nickname;
    private String imgUrl;
    private String characterImgUrl;
    private String mbti;
    private GameStatus gameStatus;
}
