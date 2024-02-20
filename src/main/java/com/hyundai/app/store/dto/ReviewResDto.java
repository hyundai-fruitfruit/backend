package com.hyundai.app.store.dto;

import com.hyundai.app.store.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 리뷰 관련 응답 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResDto {
    private int id;
    private int score;
    private String content;
    private List<String> hashtags;
    public static ReviewResDto of(Review review) {
        return ReviewResDto.builder()
                .id(review.getId())
                .score(review.getScore())
                .content(review.getContent())
                .build();
    }
}