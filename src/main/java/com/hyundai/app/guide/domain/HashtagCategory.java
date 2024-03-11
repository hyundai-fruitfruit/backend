package com.hyundai.app.guide.domain;

import lombok.*;

/**
 * @author 황수영
 * @since 2024/03/09
 * (설명)
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashtagCategory {
    private String title;
    private String korean;
}