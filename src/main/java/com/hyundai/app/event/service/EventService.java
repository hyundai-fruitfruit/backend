package com.hyundai.app.event.service;

import com.hyundai.app.event.dto.EventActiveTimeZoneDto;
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
        EventDetailResDto eventDetailResDto = eventMapper.find(eventType);
        int eventId = eventDetailResDto.getId();
        List<EventActiveTimeZoneDto> eventActiveTimeZoneDto = findEventActiveTime(eventId);
        eventDetailResDto.setActiveTimeList(eventActiveTimeZoneDto);
        return eventDetailResDto;
    }

    public EventListResDto findEventList(int storeId) {
        List<EventDetailResDto> eventDetailResDtoList = eventMapper.findEventList(storeId);

        for (EventDetailResDto eventDetailResDto : eventDetailResDtoList) {
            int eventId = eventDetailResDto.getId();
            List<EventActiveTimeZoneDto> eventActiveTimeZoneDtoList = findEventActiveTime(eventId);
            eventDetailResDto.setActiveTimeList(eventActiveTimeZoneDtoList);
        }

        EventListResDto eventListResDto = new EventListResDto(eventDetailResDtoList);
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
        List<EventActiveTimeZoneDto> eventActiveTimeZoneDto = findEventActiveTime(eventId);
        eventDetailResDto.setActiveTimeList(eventActiveTimeZoneDto);
        return eventDetailResDto;
    }

    private List<EventActiveTimeZoneDto> findEventActiveTime(int eventId) {
        List<EventActiveTimeZoneDto> eventActiveTimeZoneDto = eventActiveTimeMapper.findByEventId(eventId);
        return eventActiveTimeZoneDto;
    }

    public EventDetailResDto save(int storeId, EventSaveReqDto eventSaveReqDto) {
        int eventId = saveEvent(storeId, eventSaveReqDto);
        saveEventActiveTime(eventId, eventSaveReqDto);
        return find(storeId, eventId);
    }

    private int saveEvent(int storeId, EventSaveReqDto eventSaveReqDto) {
        eventSaveReqDto.setStoreId(storeId);
        eventMapper.save(eventSaveReqDto);
        int eventId = eventSaveReqDto.getId();
        return eventId;
    }

    private void saveEventActiveTime(int eventId, EventSaveReqDto eventSaveReqDto) {
        eventSaveReqDto.setDefaultActiveTimeIfEmpty();
        eventSaveReqDto.getActiveTimeList().forEach(eventActiveTime -> {
            eventActiveTime.setEventId(eventId);
            eventActiveTimeMapper.save(eventActiveTime);
        });
    }
}
