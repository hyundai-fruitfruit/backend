package com.hyundai.app.friend.dto;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;

/**
 * @author 엄상은
 * @since 2024/02/13
 * MBTI 결과를 받아올 응답 객체
 */
@Getter
@NoArgsConstructor
public class MbtiSaveReqDto {
    @PositiveOrZero
    private int scoreI;

    @PositiveOrZero
    private int scoreE;

    @PositiveOrZero
    private int scoreS;

    @PositiveOrZero
    private int scoreN;

    @PositiveOrZero
    private int scoreF;

    @PositiveOrZero
    private int scoreT;

    @PositiveOrZero
    private int scoreP;

    @PositiveOrZero
    private int scoreJ;
}
