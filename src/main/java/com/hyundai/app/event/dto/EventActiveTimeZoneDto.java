package com.hyundai.app.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 엄상은
 * @since 2024/02/19
 * 이벤트 활성화 시작, 종료 시간 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventActiveTimeZoneDto {
    private int opennedAt;
    private int closedAt;
}