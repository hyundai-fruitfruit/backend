package com.hyundai.app.store.domain;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/02/13
 * 해시태그 도메인
 */

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag {
    private int id;
    private String name;
    private String category;

    public static Hashtag from(int id) {
        Hashtag hashtag = new Hashtag();
        hashtag.id = id;
        return hashtag;
    }

}
