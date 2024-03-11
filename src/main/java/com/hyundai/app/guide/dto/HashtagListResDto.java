package com.hyundai.app.guide.dto;

import com.hyundai.app.store.domain.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/26
 * 해시태그 리스트 DTO
 */
@Getter
@AllArgsConstructor
public class HashtagListResDto {

    private String title;
    // private String korean;
    private List<Hashtag> hashtags;
}