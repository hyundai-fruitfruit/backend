package com.hyundai.app.event.mapper;

import com.hyundai.app.event.domain.MemberEvent;
import com.hyundai.app.event.dto.MemberEventDetailsResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/21
 * 이벤트에 참여한 회원 Mapper
 */
@Mapper
public interface MemberEventMapper {
    void saveMemberEvent(MemberEvent memberEvent);
    List<MemberEventDetailsResDto> getMemberEventDetails(int eventId);
}
