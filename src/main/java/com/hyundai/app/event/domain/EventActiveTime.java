package com.hyundai.app.event.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 활성화 시간 엔티티
 */
@Getter
@RequiredArgsConstructor
public class EventActiveTime {
    private int id;
    private int eventId;
    private int opennedAt;
    private int closedAt;

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 이벤트 활성화 시간 생성자
     */
    @JsonCreator
    public EventActiveTime(@JsonProperty("opennedAt") int opennedAt, @JsonProperty("closedAt") int closedAt) {
        this.opennedAt = opennedAt;
        this.closedAt = closedAt;
    }

    /**
     * @author 엄상은
     * @since 2024/02/19
     * 이벤트 ID 설정
     */
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
