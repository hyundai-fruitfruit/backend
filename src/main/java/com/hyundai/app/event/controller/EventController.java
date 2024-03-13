package com.hyundai.app.event.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.event.dto.EventDetailResDto;
import com.hyundai.app.event.dto.EventParticipateResDto;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.service.EventService;
import com.hyundai.app.security.methodparam.MemberId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Random;

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
    private static final Random random = new Random();

    /**
     * @author 엄상은
     * @since 2024/03/04
     * 유저용 현재 열린 이벤트 전체 조회 API
     */
    @GetMapping
    @ApiOperation("유저용 현재 열린 이벤트 전체 조회 API")
    public AdventureOfHeendyResponse<List<EventDetailResDto>> findCurrentEventByEventType(@ApiIgnore @MemberId String memberId) {
        return AdventureOfHeendyResponse.success("이벤트 목록을 가져왔습니다.", eventService.findCurrentEventByEventType(memberId));
    }

    /**
     * @author 엄상은
     * @since 2024/02/23
     * 유저용 이벤트 참여 API
     */
    @PostMapping("{eventId}/participate")
    @ApiOperation("유저용 이벤트 참여 API")
    public AdventureOfHeendyResponse<EventParticipateResDto> participateEvent(@ApiIgnore @MemberId String memberId,
                                                                              @PathVariable int eventId){
        return AdventureOfHeendyResponse.success("이벤트 참여에 성공했습니다.", eventService.participateEvent(memberId, eventId));
    }

    /**
     * @author 황수영
     * @since 2024/02/14
     * 이벤트 종류에 따른 랜덤 스팟 조회
     */
    @GetMapping("/random-spot")
    @ApiOperation("이벤트 종류에 따른 랜덤 스팟 조회")
    public ResponseEntity<EventDetailResDto> getRandomSpotByEventType(@RequestParam String eventType) {
        EventDetailResDto eventDetailResDto = eventService.getRandomSpotDetail(eventType);
        return new ResponseEntity<>(eventDetailResDto, HttpStatus.ACCEPTED);
    }
}
