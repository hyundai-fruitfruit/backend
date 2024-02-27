package com.hyundai.app.store.service;

import com.hyundai.app.guide.dto.HashtagListResDto;
import com.hyundai.app.store.dto.StoreResDto;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/26
 * 매장/리뷰 해시태그 관련 서비스단
 */
public interface HashtagService {
    List<HashtagListResDto> getHashtagAllByGuideType(String guideType);
    List<StoreResDto> findStoresByMostSavedHashtags(int hashtagId);
}