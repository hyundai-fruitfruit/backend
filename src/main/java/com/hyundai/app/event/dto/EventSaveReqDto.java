package com.hyundai.app.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyundai.app.event.domain.EventActiveTime;
import com.hyundai.app.event.enumType.EventType;
import com.hyundai.app.event.enumType.RewardType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/19
 * 이벤트 저장 요청 객체
 */
@Getter
public class EventSaveReqDto {
    private int id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishedAt;
    private List<EventActiveTime> activeTimeList;
    private int maxCount;
    private EventType eventType;
    private RewardType rewardType;
    private String reward;
    private String content;
    private int storeId;
    private int couponId;

    public void setDefaultActiveTimeIfEmpty() {
        if (this.activeTimeList == null || this.activeTimeList.isEmpty()) {
            this.activeTimeList = List.of(new EventActiveTime(10, 22));
        }
    }

    public void setId(int eventId) {
        this.id = eventId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}