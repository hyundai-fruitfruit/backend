package com.hyundai.app.member.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author 황수영
 * @since 2024/02/12
 * 헤더 타입
 */
@Getter
@AllArgsConstructor
public enum Header {
    AUTH("Authorization"),
    BEARER("Bearer ");
    private final String value;
}
