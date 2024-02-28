package com.hyundai.app.event.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.dto.EventListResDto;
import com.hyundai.app.event.dto.EventSaveReqDto;
import com.hyundai.app.event.dto.MemberEventDetailsResDto;
import com.hyundai.app.event.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/19
 * 어드민용 이벤트 컨트롤러
 */
@Api("어드민용 이벤트 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/events")
@RestController
public class AdminEventController {
    private final EventService eventService;

    @GetMapping
    @ApiOperation("어드민용 해당 지점 이벤트 전체 조회 API")
    public AdventureOfHeendyResponse<EventListResDto> findEventList(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("지점의 이벤트 목록을 가져왔습니다.", eventService.findEventList(storeId, page, size));
    }

    @PostMapping
    @ApiOperation("어드민용 이벤트 작성 API")
    public AdventureOfHeendyResponse<EventDetailResDto> save(@RequestBody EventSaveReqDto eventSaveReqDto) {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("이벤트를 저장했습니다.", eventService.save(storeId, eventSaveReqDto));
    }

    @GetMapping("/{eventId}")
    @ApiOperation("어드민용 이벤트 상세조회 API")
    public AdventureOfHeendyResponse<EventDetailResDto> findEvent(@PathVariable final int eventId) {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("지점의 이벤트 상세 정보를 가져왔습니다.", eventService.find(storeId, eventId));
    }

    @PatchMapping("/{eventId}")
    @ApiOperation("어드민용 이벤트 수정 API")
    public AdventureOfHeendyResponse<EventSaveReqDto> update(@PathVariable final int eventId, @RequestBody EventSaveReqDto eventSaveReqDto) {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("이벤트를 수정했습니다.", eventService.update(storeId, eventId, eventSaveReqDto));
    }

    @DeleteMapping("/{eventId}")
    @ApiOperation("어드민용 이벤트 삭제 API")
    public AdventureOfHeendyResponse<Void> delete(@PathVariable final int eventId) {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("이벤트를 삭제했습니다.", eventService.delete(storeId, eventId));
    }

    /**
     * @author 최성혁
     * @since 2024/02/27
     * 이벤트 참여자 회원 목록 조회
     */

    @GetMapping("/{eventId}/participants")
    @ApiOperation("어드민용 이벤트 참여자 상세정보 조회 API")
    public AdventureOfHeendyResponse<List<MemberEventDetailsResDto>> findEventParticipants(@PathVariable final int eventId) {
        // TODO: 지점 ID 받아오는 부분 수정
        int storeId = 1;
        return AdventureOfHeendyResponse.success("이벤트의 참여자 상세 정보를 가져왔습니다.", eventService.findEventParticipants(eventId,storeId));
    }
}
