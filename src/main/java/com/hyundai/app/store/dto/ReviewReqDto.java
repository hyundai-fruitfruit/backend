package com.hyundai.app.store.dto;

import lombok.Getter;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 리뷰 관련 요청 DTO
 */
@Getter
public class ReviewReqDto {
    private int id;
    private int score;
    private String content;
    private List<Integer> hashtagIds;
    // 이미지 이후에 추가
}