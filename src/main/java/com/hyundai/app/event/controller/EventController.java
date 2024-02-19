package com.hyundai.app.event.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("유저용 이벤트 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
@RestController
public class EventController {
    private final EventService eventService;

    @GetMapping
    @ApiOperation("유저용 현재 열린 이벤트 조회 API")
    public AdventureOfHeendyResponse<EventDetailResDto> findCurrentEventByEventType(@RequestParam EventType eventType) {
        return AdventureOfHeendyResponse.success("이벤트 목록을 가져왔습니다.", eventService.findCurrentEventByEventType(eventType));
    }
}
