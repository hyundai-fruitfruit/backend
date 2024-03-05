package com.hyundai.app.event.mapper;

import com.hyundai.app.common.entity.IdWithCriteria;
import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.dto.EventSaveReqDto;
import com.hyundai.app.event.enumType.EventType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 Mapper
 */
@Mapper
public interface EventMapper {
    List<EventDetailResDto> findCurrentEventByEventType(String memberId);

    List<EventDetailResDto> findEventList(IdWithCriteria idWithCriteria);

    EventDetailResDto findById(int eventId);

    int save(EventSaveReqDto eventSaveReqDto);

    void update(EventSaveReqDto eventSaveReqDto);

    void delete(int eventId);

    List<EventDetailResDto> findEventAllByEventType(EventType eventType);

    void increaseVisitedCount(int eventId);
}