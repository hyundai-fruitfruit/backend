package com.hyundai.app.event.mapper;

import com.hyundai.app.event.domain.MemberEvent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 엄상은
 * @since 2024/02/21
 * 이벤트에 참여한 회원 Mapper
 */
@Mapper
public interface MemberEventMapper {
    void saveMemberEvent(MemberEvent memberEvent);
}
