package com.hyundai.app.store.service;

import com.hyundai.app.guide.GuideType;
import com.hyundai.app.guide.dto.HashtagListResDto;
import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.Store;
import com.hyundai.app.store.mapper.HashtagMapper;
import com.hyundai.app.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/26
 * (설명)
 */
@Log4j
@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService{

    private final StoreMapper storeMapper;
    private final HashtagMapper hashtagMapper;


    /**
     * @author 황수영
     * @since 2024/02/26
     * 분류별 해시태그 전체 조회
     */
    @Override
    public List<HashtagListResDto> getHashtagAllByGuideType(String guideType) {
        log.debug("분류별 해시태그 조회 분류 : " + guideType);
        GuideType guideTypeEnum = GuideType.valueOf(guideType.toUpperCase()); // 식당 분류
        log.debug("분류별 해시태그 조회 분류 : " + guideTypeEnum);

        // TODO: 캐싱 필요!
        List<HashtagListResDto> result = new ArrayList<>(); // 식당의 모든 해시태그들 조회

        for (String category : guideTypeEnum.getCategory()) {
            log.debug("분류별 해시태그 조회 => category : " + category);
            List<Hashtag> hashtags = hashtagMapper.getHashtagByCategory(category);
            HashtagListResDto hashtagListResDto = new HashtagListResDto(category, hashtags);
            result.add(hashtagListResDto);
            log.debug("분류별 해시태그 조회" + hashtagListResDto);
        }
        // 정렬된 순서대로 조회
        return result;
    }


    /**
     * @author 황수영
     * @since 2024/02/14
     * 해당 해시태그가 가장 많이 저장된 매장들 조회
     */
    @Override
    public List<Store> findStoresByMostSavedHashtags(int hashtagId) {
        List<Store> stores = storeMapper.getStoresByHashtagId(hashtagId);
        // 정렬된 순서대로 조회
        return stores;
    }
}