package com.hyundai.app.store.mapper;

import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 관련 mapper
 */
public interface StoreMapper {
    Store getStoreDetail(@Param("storeId") int storeId);
    List<Hashtag> getPopularHashtagsOfStore(@Param("storeId") int storeId);
}
