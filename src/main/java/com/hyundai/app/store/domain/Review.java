package com.hyundai.app.store.domain;

import com.hyundai.app.common.entity.BaseEntity;
import com.hyundai.app.store.dto.ReviewReqDto;
import lombok.*;

import java.util.List;

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
    private String memberId;
    private int storeId;
    private int isDeleted;
    private int score;
    private String content;
    private List<Integer> hashtags;

    public static Review of(String id, ReviewReqDto reviewReqDto, int storeId, String memberId) {
        return Review.builder()
                .id(id)
                .score(reviewReqDto.getScore())
                .content(reviewReqDto.getContent())
                .memberId(memberId)
                .storeId(storeId)
                .hashtags(reviewReqDto.getHashtagIds())
                .build();
    }
}