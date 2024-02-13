package com.hyundai.app.store.domain;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 도메인
 */

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    private int id;
    private String name;
    private String contactNumber;
    private String mainCategory;
    private String subCategory;
    private int floor;
    private String description;
    private int avgScore;

    // 오픈시간 / 끝나는 시간 일단 제외
}