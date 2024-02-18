package com.hyundai.app.event.dto;

import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.enumType.RewardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate startedAt;
    private LocalDate finishedAt;
    private int maxCount;
    private int visitedCount;
}
