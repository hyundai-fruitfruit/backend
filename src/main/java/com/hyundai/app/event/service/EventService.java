package com.hyundai.app.event.service;

import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.dto.EventListResDto;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 서비스
 */
@RequiredArgsConstructor
@Service
public class EventService {
    private final EventMapper eventMapper;

    public EventDetailResDto find(EventType eventType) {
        return eventMapper.find(eventType);
    }

    public EventListResDto findEventList(int storeId) {
        List<EventDetailResDto> eventDetailResDto = eventMapper.findEventList(storeId);
        EventListResDto eventListResDto = new EventListResDto(eventDetailResDto);
        return eventListResDto;
    }
}
