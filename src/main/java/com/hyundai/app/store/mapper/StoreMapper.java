package com.hyundai.app.store.mapper;

import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.Review;
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
    void saveReview(@Param("review") Review review);
    void updateAvgScore(@Param("storeId") int storeId, @Param("avgScore") double avgScore);
    void updateReviewCount(@Param("storeId") int storeId);
    List<Review> getReviews(@Param("storeId") int storeId);
    List<Store> getStoresByHashtagId(@Param("hashtagId") int hashtagId);
}