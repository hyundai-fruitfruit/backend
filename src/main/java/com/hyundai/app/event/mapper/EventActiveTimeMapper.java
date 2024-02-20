package com.hyundai.app.event.mapper;

import com.hyundai.app.event.domain.EventActiveTime;
import com.hyundai.app.event.dto.EventActiveTimeZoneDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/19
 * 이벤트 활성화 시간 MAPPER
 */
@Mapper
public interface EventActiveTimeMapper {
    int save(EventActiveTime eventActiveTime);
    List<EventActiveTimeZoneDto> findByEventId(int eventId);
}
