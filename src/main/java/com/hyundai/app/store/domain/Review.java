package com.hyundai.app.store.domain;

import com.hyundai.app.common.entity.BaseEntity;
import com.hyundai.app.store.dto.ReviewReqDto;
import lombok.*;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
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
@Log4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    private String id;
    private String memberId;
    private int storeId;
    private int isDeleted;
    private int score;
    private String content;
    private List<Hashtag> hashtags;
    private List<Image> imageList;

    public static Review of(String id, ReviewReqDto reviewReqDto, int storeId, String memberId) {
        log.debug("Review id " + id);
        List<Hashtag> hashtags = new ArrayList<>();
        for (int hashtagId : reviewReqDto.getHashtagIds()) {
            hashtags.add(Hashtag.from(hashtagId));
        }
        return Review.builder()
                .id(id)
                .score(reviewReqDto.getScore())
                .content(reviewReqDto.getContent())
                .memberId(memberId)
                .storeId(storeId)
                .hashtags(hashtags)
                .build();
    }

    public void updateImages(List<Image> imageList) {
        this.imageList = imageList;
    }

    public void updateHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}