package com.hyundai.app.event.service;

import com.hyundai.app.event.dto.EventActiveTimeZoneDto;
import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.dto.EventListResDto;
import com.hyundai.app.event.dto.EventSaveReqDto;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.mapper.EventActiveTimeMapper;
import com.hyundai.app.event.mapper.EventMapper;
import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 사용자용 + 어드민용 이벤트 서비스
 */
@Log4j
@RequiredArgsConstructor
@Service
public class EventService {
    private final EventMapper eventMapper;
    private final EventActiveTimeMapper eventActiveTimeMapper;

    public EventDetailResDto findCurrentEventByEventType(EventType eventType) {
        EventDetailResDto eventDetailResDto = eventMapper.findCurrentEventByEventType(eventType);
        if (eventDetailResDto != null) {
            log.error("해당 타입의 이벤트가 존재하지 않습니다.");
            throw new AdventureOfHeendyException(ErrorCode.EVENT_NOT_EXIST);
        }
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

        EventListResDto eventListResDto = EventListResDto.of(eventDetailResDtoList);
        return eventListResDto;
    }

    public EventDetailResDto find(int storeId, int eventId) {
        EventDetailResDto eventDetailResDto = findEventAndValidate(storeId, eventId);
        List<EventActiveTimeZoneDto> eventActiveTimeZoneDto = findEventActiveTime(eventId);
        eventDetailResDto.setActiveTimeList(eventActiveTimeZoneDto);
        return eventDetailResDto;
    }

    public EventDetailResDto findEventAndValidate(int storeId, int eventId) {
        EventDetailResDto eventDetailResDto = eventMapper.findById(eventId);
        if (eventDetailResDto == null) {
            throw new IllegalArgumentException("해당 이벤트가 존재하지 않습니다.");
        }
        if (eventDetailResDto.getStoreId() != storeId) {
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

    public EventSaveReqDto update(int storeId, int eventId, EventSaveReqDto eventSaveReqDto) {
        findEventAndValidate(storeId, eventId);
        eventSaveReqDto.setId(eventId);
        eventMapper.update(eventSaveReqDto);
        return eventSaveReqDto;
    }

    public Void delete(int storeId, int eventId) {
        findEventAndValidate(storeId, eventId);
        eventMapper.delete(eventId);
        return null;
    }

    /**
     * @author 황수영
     * @since 2024/02/20
     * 이벤트 종류에 따른 랜덤 스팟 조회
     */
    public EventDetailResDto getRandomSpotDetail(EventType eventType) {
        EventType randomSpotType = EventType.getRandomEventType(eventType);
        List<EventDetailResDto> events = eventMapper.findEventAllByEventType(randomSpotType);
        log.debug("입력된 이벤트 타입 : " + eventType + " => " + " 랜덤 스팟 타입 : " + randomSpotType);

        if (events.isEmpty()) {
            log.error("해당 타입의 이벤트가 존재하지 않습니다.");
            throw new AdventureOfHeendyException(ErrorCode.EVENT_NOT_EXIST);
        }
        EventDetailResDto eventDetailResDto = events.get(0); // TODO: 후에 로직 적용
        List<EventActiveTimeZoneDto> activeTimeZoneList = eventActiveTimeMapper.findByEventId(eventDetailResDto.getId());
        eventDetailResDto.setActiveTimeList(activeTimeZoneList);
        log.debug("랜덤 스팟 상세 정보 조회 : " + eventDetailResDto);
        return eventDetailResDto;
    }
}
