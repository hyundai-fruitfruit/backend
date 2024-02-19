package com.hyundai.app.event.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 활성화 시간 엔티티
 */
@NoArgsConstructor
@AllArgsConstructor
public class EventActiveTime {
    private int id;
    private int eventId;
    private int opennedAt;
    private int closedAt;

    @JsonCreator
    public EventActiveTime(@JsonProperty("opennedAt") int opennedAt, @JsonProperty("closedAt") int closedAt) {
        this.opennedAt = opennedAt;
        this.closedAt = closedAt;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
