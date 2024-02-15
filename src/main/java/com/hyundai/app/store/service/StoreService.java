package com.hyundai.app.store.service;

import com.hyundai.app.store.dto.StoreResDto;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 관련 서비스단
 */
public interface StoreService {

    StoreResDto getStoreDetail(int storeId);
}