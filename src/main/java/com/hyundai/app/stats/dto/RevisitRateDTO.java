package com.hyundai.app.stats.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author : 오수영
 * @since : 2024/03/11
 * 통계 관련 DTO
 */
@Getter
@Setter
public class RevisitRateDTO {
    private Long storeId;
    private String eventParticipation;
    private Integer totalVisitors;
    private Integer repeatVisitors;
    private Double revisitRate;
}

