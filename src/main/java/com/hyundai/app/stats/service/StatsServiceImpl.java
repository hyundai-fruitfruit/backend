package com.hyundai.app.stats.service;

import com.hyundai.app.stats.dto.PromotionEffectStatsDTO;
import com.hyundai.app.stats.dto.RevisitRateDTO;
import com.hyundai.app.stats.dto.StoreAgeGenderStatsDTO;
import com.hyundai.app.stats.dto.StoreUsageStatsDTO;
import com.hyundai.app.stats.mapper.StatsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author : 오수영
 * @since : 2024/03/11
 * 통계 관련 ServiceImpl
 */
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsMapper statsMapper;

    @Override
    public List<StoreUsageStatsDTO> findAllStats() {
        return statsMapper.findAllStoreUsageStats();
    }

    @Override
    public List<StoreAgeGenderStatsDTO> findAllStoreAgeGenderStats() {
        return statsMapper.findAllStoreAgeGenderStats();
    }

    @Override
    public List<RevisitRateDTO> findAllRevisitRates() {
        return statsMapper.findAllRevisitRates();
    }

    @Override
    public List<PromotionEffectStatsDTO> findAllPromotionEffectStats() {
        return statsMapper.findAllPromotionEffectStats();
    }
}