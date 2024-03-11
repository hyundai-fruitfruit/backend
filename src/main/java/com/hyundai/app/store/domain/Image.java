package com.hyundai.app.store.domain;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/03/04
 * (설명)
 */

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    private int id;
    private String imgUrl;
    private int storeId;
    private String memberId;
    private String reviewId;
}