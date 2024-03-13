package com.hyundai.app.stats.service;

import com.hyundai.app.stats.dto.PromotionEffectStatsDTO;
import com.hyundai.app.stats.dto.RevisitRateDTO;
import com.hyundai.app.stats.dto.StoreAgeGenderStatsDTO;
import com.hyundai.app.stats.dto.StoreUsageStatsDTO;
import java.util.List;

/**
 * @author : 오수영
 * @since : 2024/03/11
 * 통계 관련 Service
 */
public interface StatsService {
    List<StoreUsageStatsDTO> findAllStats();
    List<StoreAgeGenderStatsDTO> findAllStoreAgeGenderStats();
    List<RevisitRateDTO> findAllRevisitRates();

    List<PromotionEffectStatsDTO> findAllPromotionEffectStats();

}
