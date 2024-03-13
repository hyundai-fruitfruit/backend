package com.hyundai.app.stats.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : 오수영
 * @since : 2024/03/11
 * 통계 관련 DTO
 */
@Getter
@Setter
public class PromotionEffectStatsDTO {
    private Long storeId;
    private Long memberId;
    private Long couponId;
    private String gender;
    private String ageGroup;
    private Integer usageCount;
}
