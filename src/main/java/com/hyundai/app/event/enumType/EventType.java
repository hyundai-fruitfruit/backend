package com.hyundai.app.event.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    SHOPPING("쇼핑");

    private final String description;
}
