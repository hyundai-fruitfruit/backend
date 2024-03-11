package com.hyundai.app.store.mapper;

import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.StoreHashtag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/17
 * 해시태그 관련 매퍼
 */
public interface HashtagMapper {

    Hashtag getHashtag(@Param("hashtagId") int hashtagId);
    void createStoreHashtag(@Param("storeId") int storeId, @Param("hashtagId") int hashtagId);
    StoreHashtag getStoreHashtag(@Param("storeId") int storeId, @Param("hashtagId") int hashtagId);
    void updateStoreHashtag(@Param("storeId") int storeId, @Param("hashtagId") int hashtagId);
    List<Hashtag> getHashtagByCategory(@Param("category") String category);
    void createReviewHashtag(@Param("reviewId") String reviewId, @Param("hashtagId") int hashtagId);
    List<Hashtag> getHashtagByReviewId(@Param("reviewId") String reviewId);


}