package com.hyundai.app.guide.dto;

import com.hyundai.app.guide.GuideType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/26
 * 챗봇 가이드 타입 Res DTO
 */
@Getter
@AllArgsConstructor
public class GuideTypeResDto {
    private GuideType guideType;
    private String korean;
    private final List<String> category;
}