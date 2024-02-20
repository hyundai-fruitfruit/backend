package com.hyundai.app.event.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Random;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 이벤트 타입 ENUM 객체
 */
@Getter
@AllArgsConstructor
public enum EventType {
    CAFE("카페"),
    RESTAURANT("식당"),
    SHOPPING("쇼핑"),
    RANDOM("랜덤");

    private final String description;
    private static final Random random = new Random();

    /**
     * @author 황수영
     * @since 2024/02/20
     * 랜덤 타입 반환
     */
    public static EventType getRandomEventType(EventType eventType) {
        if (eventType.equals(EventType.RANDOM)) {
            int randomIndex = random.nextInt(EventType.values().length - 1);
            return List.of(CAFE, RESTAURANT, SHOPPING).get(randomIndex);
        }
        return eventType;
    }
}