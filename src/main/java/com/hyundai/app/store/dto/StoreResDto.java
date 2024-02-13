package com.hyundai.app.store.dto;

import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 상세 정보 조회 Res DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResDto {
    private int id;
    private String name;
    private String description;
    private int phone;
    private int floor;
    private int avgScore;

    private List<Hashtag> popularHashtags; // 사실 이름만 필요함!
    private List<ReviewResDto> reviews;

    public static StoreResDto of(Store store) {
        return StoreResDto.builder()
                .id(store.getId())
                .description(store.getDescription())
                .name(store.getName())
                .avgScore(store.getAvgScore())
                .floor(store.getFloor())
                .build();
    }

    // TODO: 해당 매장의 리뷰들 조회
//    public void updateReviews(List<ReviewResDto> reviews) {
//        this.reviews = reviews;
//    }

    public void updatePopularHashtags(List<Hashtag> popularHashtags) {
        this.popularHashtags = popularHashtags;
    }
}