package com.hyundai.app.guide;

import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import com.hyundai.app.guide.dto.GuideTypeResDto;
import com.hyundai.app.guide.dto.HashtagListResDto;
import com.hyundai.app.store.dto.StoreResDto;
import com.hyundai.app.store.service.HashtagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/26
 * (설명)
 */
@Log4j
@Api("대장 흰디 가이드용 API")
@RestController
@RequestMapping("/api/v1/heendy-guide")
public class GuideController {

    @Autowired
    @Qualifier("hashtagServiceImpl")
    private HashtagService hashtagService;

    @Autowired
    private GuideService guideService;

    @GetMapping
    @ApiOperation("전체 가이드 조회")
    public ResponseEntity<List<GuideTypeResDto>> getGuideAll() {
        List<GuideTypeResDto> guideTypeList = guideService.getGuideAll();
        log.debug("전체 가이드 조회 : " + guideTypeList);
        return new ResponseEntity<>(guideTypeList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{guideType}")
    @ApiOperation("분류별 해시태그 조회 - 해당 분류의 모든 해시태그 조회하기 - 식당/쇼핑 매장")
    public ResponseEntity<List<HashtagListResDto>> getGuideByCategory(@PathVariable("guideType") String guideType) {
        log.debug("분류별 해시태그 조회 =>  guideType : " + guideType);
        List<HashtagListResDto> hashtagListResDto = guideService.getHashtagCategoryAll(guideType);
        return new ResponseEntity<>(hashtagListResDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/hashtag")
    @ApiOperation("해시 태그 선택 시, 관련 식당들 조회")
    public ResponseEntity<List<StoreResDto>> findStoresByHashtags(@RequestParam("hashtagId")int hashtagId) {
        log.debug("해시 태그 선택 시, 관련 식당들 조회 => 해시 태그 : " + hashtagId);
        List<StoreResDto> stores = hashtagService.findStoresByMostSavedHashtags(hashtagId);
        if (stores.isEmpty()) {
            log.error("해당 해시 태그의 식당들이 존재하지 않습니다. => 해시 태그 : " + hashtagId);
            throw new AdventureOfHeendyException(ErrorCode.STORE_NOT_EXIST);
        }
        return new ResponseEntity<>(stores, HttpStatus.ACCEPTED);
    }
}