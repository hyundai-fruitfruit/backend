package com.hyundai.app.event.service;

import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.dto.EventListResDto;
import com.hyundai.app.event.dto.EventSaveReqDto;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.mapper.EventActiveTimeMapper;
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
    private final EventActiveTimeMapper eventActiveTimeMapper;

    public EventDetailResDto find(EventType eventType) {
        return eventMapper.find(eventType);
    }

    public EventListResDto findEventList(int storeId) {
        List<EventDetailResDto> eventDetailResDto = eventMapper.findEventList(storeId);
        EventListResDto eventListResDto = new EventListResDto(eventDetailResDto);
        return eventListResDto;
    }

    public EventDetailResDto find(int storeId, int eventId) {
        EventDetailResDto eventDetailResDto = eventMapper.findById(eventId);
        if (eventDetailResDto == null) {
            throw new IllegalArgumentException("해당 이벤트가 존재하지 않습니다.");
        }
        else if (eventDetailResDto.getStoreId() != storeId) {
            throw new IllegalArgumentException("해당 지점의 이벤트가 아닙니다.");
        }
        return eventDetailResDto;
    }

    public EventDetailResDto save(int storeId, EventSaveReqDto eventSaveReqDto) {
        int eventId = saveEvent(storeId, eventSaveReqDto);
        saveEventActiveTime(eventId, eventSaveReqDto);
        return find(storeId, eventId);
    }

    public int saveEvent(int storeId, EventSaveReqDto eventSaveReqDto) {
        eventSaveReqDto.setStoreId(storeId);
        eventMapper.save(eventSaveReqDto);
        int eventId = eventSaveReqDto.getId();
        return eventId;
    }

    public void saveEventActiveTime(int eventId, EventSaveReqDto eventSaveReqDto) {
        eventSaveReqDto.setDefaultActiveTimeIfEmpty();
        eventSaveReqDto.getActiveTimeList().forEach(eventActiveTime -> {
            eventActiveTime.setEventId(eventId);
            eventActiveTimeMapper.save(eventActiveTime);
        });
    }
}
