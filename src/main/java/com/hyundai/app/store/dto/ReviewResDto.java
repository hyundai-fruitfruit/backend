package com.hyundai.app.store.dto;

import lombok.Getter;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 리뷰 관련 응답 DTO
 */
@Getter
public class ReviewResDto {
    private int id;
    private int score;
    private String content;
    private List<String> hashtags;
}