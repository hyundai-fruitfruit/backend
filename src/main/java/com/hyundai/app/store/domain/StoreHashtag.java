package com.hyundai.app.store.domain;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 - 해시태그 관계 도메인
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreHashtag {
    private int id;
    private int hashtagId;
    private int storeId;
    private int count;
}