package com.hyundai.app.store.service;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.Image;
import com.hyundai.app.store.domain.Review;
import com.hyundai.app.store.domain.Store;
import com.hyundai.app.store.dto.ReviewReqDto;
import com.hyundai.app.store.dto.StoreResDto;
import com.hyundai.app.store.mapper.HashtagMapper;
import com.hyundai.app.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static com.hyundai.app.exception.ErrorCode.*;

/**
 * @author 황수영
 * @since 2024 /02/13
 * 매장 관련 서비스단 - 구현체
 */
@Log4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private static final int REVIEW_MIN_LENGTH = 5;
    private static final int SCORE_MAX_LENGTH = 5;

    private final StoreMapper storeMapper;
    private final HashtagMapper hashtagMapper;
    private final S3ImageUploadService s3ImageUploadService;

    /**
     * @author 황수영
     * @since 2024/02/14
     * 매장 상세 정보/해시태그/이미지/리뷰(이미지 포함) 조회
     */
    @Override
    public StoreResDto getStoreDetail(int storeId) {
        Store store = storeMapper.getStoreDetail(storeId);
        if (store == null) {
            log.error("해당하는 매장 번호가 존재하지 않습니다. storeId : " + storeId);
            throw new AdventureOfHeendyException(STORE_NOT_EXIST);
        }
        log.debug("매장 번호 :" + storeId + " 정보 조회 : " + store);
        List<Hashtag> popularHashtags = storeMapper.getPopularHashtagsOfStore(storeId);
        log.debug("가장 많이 선택된 해시태그들 5개 조회 : " + popularHashtags.toString());

        List<Review> reviews = storeMapper.getReviews(storeId);
        for (Review review : reviews) {
            List<Image> images = storeMapper.getImageByReviewId(review.getId());
            review.updateImages(images);

            List<Hashtag> hashtags = hashtagMapper.getHashtagByReviewId(review.getId());
            review.updateHashtags(hashtags);
        }
        log.debug("해당 매장의 전체 리뷰 조회 : " + reviews);

        StoreResDto storeResDto = StoreResDto.of(store);
        storeResDto.updatePopularHashtags(popularHashtags);
        storeResDto.updateReviews(reviews);
        return storeResDto;
    }

    /**
     * @author 황수영
     * @since 2024/02/14
     * 리뷰 작성
     */
    @Override
    @Transactional
    public void createReview(int storeId, String memberId, ReviewReqDto reviewReqDto, List<MultipartFile> imagelist) {
        validateReviewRequest(reviewReqDto);
        String reviewId = UUID.randomUUID().toString();
        Review review = Review.of(reviewId, reviewReqDto, storeId, memberId);
        storeMapper.saveReview(review);
        for (Hashtag hashtag : review.getHashtags()) {
            hashtagMapper.createReviewHashtag(review.getId(), hashtag.getId());
        }
        log.debug("리뷰 작성" + review);

        // 이미지 추가
        if (imagelist != null) {
            List<String> imageUrlList = s3ImageUploadService.uploadReviewImages(imagelist);
            for (String imageUrl :  imageUrlList) {
                log.debug("이미지 추가 reviewId : " + reviewId + ", memberId : " + memberId
                       + ", storeId : " + storeId + ", imageUrl : " + imageUrl);
                storeMapper.saveReviewImage(reviewId, memberId, storeId, imageUrl);
            }
        }

        // 해시 태그 추가
        createStoreHashtag(storeId, reviewReqDto.getHashtagIds());

        //평점 업데이트
        double newAvgScore = calcAvgScore(storeId, reviewReqDto.getScore());
        storeMapper.updateAvgScore(storeId, newAvgScore);
        storeMapper.updateReviewCount(storeId);
    }

    /**
     * @author 황수영
     * @since 2024/02/14
     * 평점 업데이트 계산
     */
    private double calcAvgScore(int storeId, int newScore) {
        Store store = storeMapper.getStoreDetail(storeId);
        if (store == null) {
            throw new AdventureOfHeendyException(STORE_NOT_EXIST);
        }
        int reviewCount = store.getReviewCount();
        float avgScore = store.getAvgScore();
        log.debug("별점 업데이트 => reviewCount : " + reviewCount + ", avgScore : " + avgScore);

        double resultScore = ((avgScore * reviewCount) + newScore) / (reviewCount + 1);
        double formattedScore = Math.round(resultScore * 10.0) / 10.0;
        log.debug("별점 결과 : " + formattedScore);
        return formattedScore;
    }

    /**
     * @author 황수영
     * @since 2024/02/14
     * 리뷰 유효성 검증 로직
     */
    public void validateReviewRequest(ReviewReqDto reviewReqDto) {
        if (reviewReqDto.getContent().length() < REVIEW_MIN_LENGTH) {
            log.error("리뷰 길이가 5자 이상이어야 합니다.");
            throw new AdventureOfHeendyException(REVIEW_CONTENT_INVALID);
        }
        if (reviewReqDto.getScore() > SCORE_MAX_LENGTH) {
            log.error("평점은 5점 이내이어야 합니다.");
            throw new AdventureOfHeendyException(REVIEW_SCORE_INVALID);
        }
    }

    /**
     * @author 황수영
     * @since 2024/02/17
     * 리뷰 작성 시, 매장별 해시태그 리스트 추가
     */
    @Transactional
    @Override
    public void createStoreHashtag(int storeId, List<Integer> hashtagIds) {
        log.error("매장 id : " + storeId + " 해시태그 id들 : " + hashtagIds + " 추가");

        for (int hashtagId : hashtagIds) {
            validateIfHashtagIdExist(hashtagId);
            handleStoreHashtag(storeId, hashtagId);
        }
    }

    /**
     * @author 황수영
     * @since 2024/02/17
     * 리뷰 작성 시, 매장별 해시태그 개별 추가 (긴 메소드 분리 용도)
     */
    private void handleStoreHashtag(int storeId, int hashtagId) {
        if (hashtagMapper.getStoreHashtag(storeId, hashtagId) != null) {
            log.debug("해시태그 id: " + hashtagId + " 이미 있을 경우 count만 증가");
            hashtagMapper.updateStoreHashtag(storeId, hashtagId);
        } else {
            log.debug("해시태그 id: " + hashtagId + " 없을 경우 새로 생성");
            hashtagMapper.createStoreHashtag(storeId, hashtagId);
        }
    }

    /**
     * @author 황수영
     * @since 2024/02/17
     * 해시 태그 아이디가 없을 경우 에러 반환
     */
    private void validateIfHashtagIdExist(int hashtagId) {
        if (hashtagMapper.getHashtag(hashtagId) == null) {
            log.error("해시태그 id: " + hashtagId + "가 존재하지 않습니다.");
            throw new AdventureOfHeendyException(HASHTAG_NOT_EXIST);
        }
    }
}
