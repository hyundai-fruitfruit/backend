package com.hyundai.app.store.service;

import com.hyundai.app.store.dto.ReviewReqDto;
import com.hyundai.app.store.dto.StoreResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 관련 서비스단
 */
public interface StoreService {

    StoreResDto getStoreDetail(int storeId);
    void createReview(int storeId, String memberId, ReviewReqDto reviewReqDto, List<MultipartFile> imagelist);
    void createStoreHashtag(int storeId, List<Integer> hashtagIds);
}
