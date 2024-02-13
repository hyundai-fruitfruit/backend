package com.hyundai.app.store.service;

import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.Store;
import com.hyundai.app.store.dto.StoreResDto;
import com.hyundai.app.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 관련 서비스단 - 구현체
 */
@Log4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreMapper storeMapper;

    @Override
    public StoreResDto getStoreDetail(int storeId) {
        Store store = storeMapper.getStoreDetail(storeId);
        log.debug("매장 번호 :" + storeId + " 정보 조회 : " + store.toString());
        List<Hashtag> popularHashtags = storeMapper.getPopularHashtagsOfStore(storeId);
        log.debug("가장 많이 선택된 해시태그들 5개 조회 : " + popularHashtags.toString());

        StoreResDto storeResDto = StoreResDto.of(store);
        storeResDto.updatePopularHashtags(popularHashtags);
        return storeResDto;
    }
}
