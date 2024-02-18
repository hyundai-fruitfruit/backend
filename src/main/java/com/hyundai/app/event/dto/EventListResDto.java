package com.hyundai.app.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 전체 정보 조회 응답 객체
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventListResDto {
    private List<EventDetailResDto> events;
}
