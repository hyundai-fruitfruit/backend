package com.hyundai.app.event.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 보상 타입 ENUM 객체
 */
@Getter
@AllArgsConstructor
public enum RewardType {
    COUPON("COUPON"),
    POINT("POINT"),
    GIFT("GIFT"),
    EVENT("EVENT"),
    ETC("ETC");

    private final String description;

    @Override
    public String toString() {
        return name();
    }
}
