package com.hyundai.app.guide;

import com.hyundai.app.guide.domain.Guide;
import com.hyundai.app.guide.domain.HashtagCategory;
import com.hyundai.app.guide.dto.GuideTypeResDto;
import com.hyundai.app.guide.dto.HashtagListResDto;
import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.mapper.HashtagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author 황수영
 * @since 2024/03/09
 * (설명)
 */
@Log4j
@Service
@RequiredArgsConstructor
public class GuideService {

    private final GuideMapper guideMapper;
    private final HashtagMapper hashtagMapper;

    public List<GuideTypeResDto> getGuideAll() {
        List<Guide> guideList = guideMapper.getGuideAll();
        List<GuideTypeResDto> guideTypeList = GuideType.of(guideList);
        log.debug("GuideService 전체 가이드 조회 : " + guideTypeList);
        return guideTypeList;
    }

    /**
     * @author 황수영
     * @since 2024/02/26
     * 분류별 해시태그 전체 조회
     */
    public List<HashtagListResDto> getHashtagCategoryAll(String guideType) {
        log.debug("분류별 해시태그 조회 분류 : " + guideType);
        GuideType guideTypeEnum = GuideType.valueOf(guideType.toUpperCase());
        log.debug("분류별 해시태그 조회 분류 : " + guideTypeEnum);

        List<HashtagCategory> hashtagCategories =  guideMapper.getHashtagCategoryAll();

        // TODO: 캐싱 필요!
        List<HashtagListResDto> result = new ArrayList<>();

        for (String category : guideTypeEnum.getHashtagType()) {
            List<Hashtag> hashtags = hashtagMapper.getHashtagByCategory(category);
            String korean = hashtagCategories.stream()
                    .filter(h -> h.getTitle().equals(category))
                    .findFirst()
                    .map(HashtagCategory::getKorean)
                    .orElseThrow(NoSuchElementException::new);
            // 한글로 변경
            HashtagListResDto hashtagListResDto = new HashtagListResDto(korean, hashtags);
            result.add(hashtagListResDto);
            log.debug("분류별 해시태그 조회 => category : " + category + " : " + hashtagListResDto);
        }
        return result;
    }
}
