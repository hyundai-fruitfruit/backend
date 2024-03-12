package com.hyundai.app.store.domain;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/03/09
 * 리뷰와 해시태그 매핑 테이블 도메인
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewHashtag {
    private int id;
    private int reviewId;
    private int storeId;
}