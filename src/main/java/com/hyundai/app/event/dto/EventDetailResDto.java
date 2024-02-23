package com.hyundai.app.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.enumType.RewardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 상세 조회 응답 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDetailResDto {
    private int id;
    private String title;
    private String content;
    private EventType eventType;
    private int storeId;
    private RewardType rewardType;
    private String reward;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishedAt;
    private int maxCount;
    private int visitedCount;
    private int couponId;
    private List<EventActiveTimeZoneDto> eventActiveTimeZoneDto;

    public void setActiveTimeList(List<EventActiveTimeZoneDto> eventActiveTimeZoneDto) {
        this.eventActiveTimeZoneDto = eventActiveTimeZoneDto;
    }
}
