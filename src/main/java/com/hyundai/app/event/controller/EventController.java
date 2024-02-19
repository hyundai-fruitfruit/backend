package com.hyundai.app.event.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.service.EventService;
import com.hyundai.app.security.methodparam.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 사용자용 이벤트 컨트롤러
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
@RestController
public class EventController {
    private final EventService eventService;

    @GetMapping
    public AdventureOfHeendyResponse<EventDetailResDto> findCurrentEventByEventType(@RequestParam EventType eventType) {
        return AdventureOfHeendyResponse.success("이벤트 목록을 가져왔습니다.", eventService.findCurrentEventByEventType(eventType));
    }
}
