package com.hyundai.app.store.domain;

import com.hyundai.app.common.entity.BaseEntity;
import com.hyundai.app.store.dto.ReviewReqDto;
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

    private String id;
    private int memberId;
    private int storeId;
    private int isDeleted;
    private int score;
    private String content;

    public static Review create(ReviewReqDto reviewReqDto, int storeId, int memberId) {
        return Review.builder()
                .score(reviewReqDto.getScore())
                .content(reviewReqDto.getContent())
                .memberId(memberId)
                .storeId(storeId)
                .build();
    }
}