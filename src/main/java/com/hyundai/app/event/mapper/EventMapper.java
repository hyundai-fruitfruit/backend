package com.hyundai.app.event.mapper;

import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.enumType.EventType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 Mapper
 */
@Mapper
public interface EventMapper {
    EventDetailResDto find(EventType eventType);
}
