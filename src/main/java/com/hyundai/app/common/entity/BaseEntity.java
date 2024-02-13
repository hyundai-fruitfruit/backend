package com.hyundai.app.common.entity;

import lombok.Getter;

import java.time.LocalDate;
/**
 * @author 황수영
 * @since 2024/02/12
 * 생성일, 수정일 공통 엔티티
 */

@Getter
public abstract class BaseEntity {

    private LocalDate createdAt;
    private LocalDate updatedAt;
}