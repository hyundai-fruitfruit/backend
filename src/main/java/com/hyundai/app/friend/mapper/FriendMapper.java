package com.hyundai.app.friend.mapper;

import com.hyundai.app.friend.domain.MemberConnection;
import com.hyundai.app.friend.dto.FriendDetailResponse;
import com.hyundai.app.friend.dto.FriendDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/12
 * 친구 관련 MAPPER
 */
@Mapper
public interface FriendMapper {
    MemberConnection findConnection(FriendDto friendDto);
    int save(MemberConnection memberConnection);
    List<FriendDetailResponse> findFriendList(int memberId);
    int updateMbti(MemberConnection savedMemberConnection);
}
