package com.hyundai.app.event.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.dto.EventListResDto;
import com.hyundai.app.event.dto.EventSaveReqDto;
import com.hyundai.app.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 엄상은
 * @since 2024/02/19
 * 어드민용 이벤트 컨트롤러
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/events")
@RestController
public class EventAdminController {
    private final EventService eventService;

    @GetMapping
    public AdventureOfHeendyResponse<EventListResDto> findEventList() {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("지점의 이벤트 목록을 가져왔습니다.", eventService.findEventList(storeId));
    }

    @PostMapping
    public AdventureOfHeendyResponse<EventDetailResDto> save(@RequestBody EventSaveReqDto eventSaveReqDto) {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("이벤트를 저장했습니다.", eventService.save(storeId, eventSaveReqDto));
    }

    @GetMapping("/{eventId}")
    public AdventureOfHeendyResponse<EventDetailResDto> find(@PathVariable final int eventId) {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("지점의 이벤트 상세 정보를 가져왔습니다.", eventService.find(storeId, eventId));
    }
}
