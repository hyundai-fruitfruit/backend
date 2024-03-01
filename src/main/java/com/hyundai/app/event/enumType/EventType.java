package com.hyundai.app.event.enumType;

import com.hyundai.app.exception.AdventureOfHeendyException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.hyundai.app.exception.ErrorCode.EVENT_TYPE_INVALID;

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
     * 랜덤일 경우 이벤트 타입 랜덤으로 반환하는 기능
     */
    private static EventType getRandomEventType(EventType eventType) {
        if (eventType.equals(EventType.RANDOM)) {
            int randomIndex = random.nextInt(EventType.values().length - 1);
            return List.of(CAFE, RESTAURANT, SHOPPING).get(randomIndex);
        }
        return eventType;
    }

    /**
     * @author 황수영
     * @since 2024/02/20
     * 입력값에 따른 이벤트 타입 반환
     */
    public static EventType of(String eventType) {
        EventType eventEnum = EventType.valueOf(eventType.toUpperCase());
        return Arrays.stream(EventType.values())
                .filter(e -> e.equals(eventEnum))
                .findFirst()
                .map(EventType::getRandomEventType)
                .orElseThrow(() -> new AdventureOfHeendyException(EVENT_TYPE_INVALID));

    }
}