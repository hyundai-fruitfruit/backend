package com.hyundai.app.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/13
 * 친구 전체 정보 조회 응답 객체
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FriendListResDto {
    private List<FriendDetailResDto> members;
}
