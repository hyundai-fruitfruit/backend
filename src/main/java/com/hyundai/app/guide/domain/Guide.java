package com.hyundai.app.guide.domain;

import lombok.*;

import java.util.List;

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
public class Guide {

    private int id;
    private String korean;
    private String guideType;
}
