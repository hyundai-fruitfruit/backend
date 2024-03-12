package com.hyundai.app.store.service;

import com.hyundai.app.guide.GuideMapper;
import com.hyundai.app.guide.GuideType;
import com.hyundai.app.guide.domain.HashtagCategory;
import com.hyundai.app.guide.dto.HashtagListResDto;
import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.Store;
import com.hyundai.app.store.dto.StoreResDto;
import com.hyundai.app.store.mapper.HashtagMapper;
import com.hyundai.app.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author 황수영
 * @since 2024/02/26
 * 해시태그 관련 서비스단
 */
@Log4j
@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService{

    private final StoreMapper storeMapper;
    private final HashtagMapper hashtagMapper;
    private final GuideMapper guideMapper;


    /**
     * @author 황수영
     * @since 2024/02/26
     * 분류별 해시태그 전체 조회
     */
    @Override
    public List<HashtagListResDto> getHashtagAllByGuideType(String guideType) {
        log.debug("분류별 해시태그 조회 분류 : " + guideType);
        GuideType guideTypeEnum = GuideType.valueOf(guideType.toUpperCase());
        log.debug("분류별 해시태그 조회 분류 : " + guideTypeEnum);

        List<HashtagCategory> hashtagCategories =  guideMapper.getHashtagCategoryAll();

        List<HashtagListResDto> result = new ArrayList<>();

        for (String category : guideTypeEnum.getHashtagType()) {
            List<Hashtag> hashtags = hashtagMapper.getHashtagByCategory(category);
            String korean = hashtagCategories.stream()
                    .filter(h -> h.getTitle().equals(category))
                    .findFirst()
                    .map(HashtagCategory::getKorean)
                    .orElseThrow(NoSuchElementException::new);
            HashtagListResDto hashtagListResDto = new HashtagListResDto(korean, hashtags);
            result.add(hashtagListResDto);
            log.debug("분류별 해시태그 조회 => category : " + category + " : " + hashtagListResDto);
        }
        return result;
    }


    /**
     * @author 황수영
     * @since 2024/02/14
     * 해당 해시태그가 가장 많이 저장된 매장들 조회
     */
    @Override
    public List<StoreResDto> findStoresByMostSavedHashtags(int hashtagId) {
        List<Store> stores = storeMapper.getStoresByHashtagId(hashtagId);
        return stores.stream().map(StoreResDto::of)
                .collect(Collectors.toList());
    }
}