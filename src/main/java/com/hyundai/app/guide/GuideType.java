package com.hyundai.app.guide;

import com.hyundai.app.guide.domain.Guide;
import com.hyundai.app.guide.dto.GuideTypeResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 황수영
 * @since 2024/02/26
 * 챗봇 가이드 타입 정의
 */
@Getter
@AllArgsConstructor
public enum GuideType {

    FLOOR(1000, "층별 설명을 듣고 싶어", List.of()),
    RANDOM_SPOT(1001, "랜덤 스팟이 뭐야?" , List.of()),
    RESTAURANT(1002, "식당 추천 해줘" , List.of("FOOD", "ATMOSPHERE", "ETC")),
    SHOPPING(1003, "쇼핑 추천 해줘", List.of("STYLE", "PRICE", "ETC"));

    private final int id;
    private final String korean;
    private final List<String> hashtagType;

    public static List<GuideTypeResDto> getGuideResDtoAll() {
        return Arrays.stream(GuideType.values())
                .map(g -> new GuideTypeResDto(g, g.korean, g.hashtagType))
                .collect(Collectors.toList());
    }

    public static List<GuideTypeResDto> of(List<Guide> guides) {

        return guides.stream()
                .map(g -> new GuideTypeResDto(GuideType.valueOf(g.getGuideType()), g.getKorean(), GuideType.valueOf(g.getGuideType()).getHashtagType()))
                .collect(Collectors.toList());
    }
}