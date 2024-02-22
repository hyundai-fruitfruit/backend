package com.hyundai.app.scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 황수영
 * @since 2024/02/22
 * job에 파라미터로 들어갈 변수값 정의
 */
@Getter
@AllArgsConstructor
public enum SchedulerVariable {
    TIME("time"),
    DEVICE_TOKEN("deviceToken");

    private final String value;
}
