package com.hyundai.app.guide;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 황수영
 * @since 2024/03/09
 * (설명)
 */
@Getter
@AllArgsConstructor
public enum HashtagType {

    FOOD(10000, "음식"),
    ATMOSPHERE(10001, "분위기"),
    ETC(10002, "가격");
    
    private final int id;
    private final String korean;
}