package com.hyundai.app.config;

import org.apache.ibatis.type.EnumTypeHandler;


public class DatabaseEnumTypeHandler<E extends Enum<E>> extends EnumTypeHandler<E> {
    public DatabaseEnumTypeHandler(Class<E> type) {
        super(type);
    }
}