package com.hyundai.app.stats.controller;

import com.hyundai.app.stats.dto.PromotionEffectStatsDTO;
import com.hyundai.app.stats.dto.RevisitRateDTO;
import com.hyundai.app.stats.dto.StoreAgeGenderStatsDTO;
import com.hyundai.app.stats.dto.StoreUsageStatsDTO;
import com.hyundai.app.stats.service.StatsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 오수영
 * @since : 2024/03/11
 * 통계 관련 컨트롤러
 */

@Api("관리자 통계 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/stats")
@RestController
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/storeUsage")
    public List<StoreUsageStatsDTO> GetStoreUsageStats() {
        return statsService.findAllStats();
    }

    @GetMapping("/storeAgeGender")
    public List<StoreAgeGenderStatsDTO> getStoreAgeGenderStats() {
        return statsService.findAllStoreAgeGenderStats();
    }

    @GetMapping("/revisitRate")
    public List<RevisitRateDTO> getRevisitRate() {
        return statsService.findAllRevisitRates();
    }

    @GetMapping("/promotionEffect")
    public List<PromotionEffectStatsDTO> getPromotionEffectStats() {
        return statsService.findAllPromotionEffectStats();
    }
}
