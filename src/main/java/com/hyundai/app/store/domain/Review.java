package com.hyundai.app.store.domain;

import com.hyundai.app.common.entity.BaseEntity;
import lombok.*;

/**
 * @author 황수영
 * @since 2024/02/13
 * 리뷰 관련 도메인
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    private int id;
    private String content;
}