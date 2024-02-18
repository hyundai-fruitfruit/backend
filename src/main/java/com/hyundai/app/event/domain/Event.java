package com.hyundai.app.event.domain;

import com.hyundai.app.common.entity.BaseEntity;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.enumType.RewardType;

import java.time.LocalDate;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 엔티티
 */
public class Event extends BaseEntity {
    private int id;
    private String title;
    private String content;
    private LocalDate startedAt;
    private LocalDate finishedAt;
    private int maxCount;
    private int visitedCount;
    private EventType eventType;
    private RewardType rewardType;
    private String reward;
    private int isDeleted;
    private int storeId;
}
