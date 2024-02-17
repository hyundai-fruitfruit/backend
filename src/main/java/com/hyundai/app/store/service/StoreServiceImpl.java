package com.hyundai.app.store.service;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.store.domain.Hashtag;
import com.hyundai.app.store.domain.Review;
import com.hyundai.app.store.domain.Store;
import com.hyundai.app.store.dto.ReviewReqDto;
import com.hyundai.app.store.dto.StoreResDto;
import com.hyundai.app.store.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hyundai.app.exception.ErrorCode.*;

/**
 *
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

    /**
     * @author 황수영
     * @since 2024/02/14
     * 매장 상세 정보/해시태그/이미지 조회
     */
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

    /**
     * @author 황수영
     * @since 2024/02/14
     * 리뷰 작성
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createReview(int storeId, int memberId, ReviewReqDto reviewReqDto) {
        validateReviewRequest(reviewReqDto);
        Review review = Review.from(reviewReqDto, storeId, memberId);
        storeMapper.saveReview(review);
        log.debug("리뷰 작성" + review);

        float newAvgScore = calcAvgScore(storeId, reviewReqDto.getScore());
        storeMapper.updateAvgScore(storeId, newAvgScore);
        storeMapper.updateReviewCount(storeId);
    }

    /**
     * @author 황수영
     * @since 2024/02/14
     * 평점 업데이트 계산
     */
    public float calcAvgScore(int storeId, int newScore) {
        Store store = storeMapper.getStoreDetail(storeId);
        int reviewCount = store.getReviewCount();
        float avgScore = store.getAvgScore();
        log.debug("별점 업데이트 => reviewCount : " + reviewCount + ", avgScore : " + avgScore);

        float resultScore = ((avgScore * reviewCount) + newScore) / (reviewCount + 1);
        log.debug("별점 결과" + resultScore);
        return resultScore;
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
}
