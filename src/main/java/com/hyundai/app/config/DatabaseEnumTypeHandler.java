package com.hyundai.app.config;

import org.apache.ibatis.type.EnumTypeHandler;

/**
 * @author 황수영
 * @since 2024/02/10
 * Enum타입을 String으로 변경하여 저장하는 핸들러 설정
 */
public class DatabaseEnumTypeHandler<E extends Enum<E>> extends EnumTypeHandler<E> {
    public DatabaseEnumTypeHandler(Class<E> type) {
        super(type);
    }
}