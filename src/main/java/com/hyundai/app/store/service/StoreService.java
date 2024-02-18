package com.hyundai.app.store.service;

import com.hyundai.app.store.dto.ReviewReqDto;
import com.hyundai.app.store.dto.StoreResDto;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 관련 서비스단
 */
public interface StoreService {

    StoreResDto getStoreDetail(int storeId);
    void createReview(int storeId, int memberId, ReviewReqDto reviewReqDto);
    void createStoreHashtag(int storeId, List<Integer> hashtagIds);
}