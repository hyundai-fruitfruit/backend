package com.hyundai.app.guide;

import com.hyundai.app.guide.dto.GuideTypeResDto;
import com.hyundai.app.guide.dto.HashtagListResDto;
import com.hyundai.app.store.domain.Store;
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

    @GetMapping
    @ApiOperation("전체 가이드 조회")
    public ResponseEntity<List<GuideTypeResDto>> getGuideAll() {
        List<GuideTypeResDto> guideTypeList = GuideType.getGuideResDtoAll();
        log.debug("전체 가이드 조회 : " + guideTypeList);
        return new ResponseEntity<>(guideTypeList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{guideType}")
    @ApiOperation("분류별 해시태그 조회 - 해당 분류의 모든 해시태그 조회하기 - 식당/쇼핑 매장")
    public ResponseEntity<List<HashtagListResDto>> getGuideByCategory(@PathVariable("guideType") String guideType) {
        log.debug("분류별 해시태그 조회 =>  guideType : " + guideType);
        List<HashtagListResDto> hashtagListResDto = hashtagService.getHashtagAllByGuideType(guideType);
        return new ResponseEntity<>(hashtagListResDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/hashtag")
    @ApiOperation("해시 태그 선택 시, 관련 식당들 조회")
    public ResponseEntity<List<Store>> findStoresByHashtags(@RequestParam("hashtagId")int hashtagId) {
        log.debug("해시 태그 선택 시, 관련 식당들 조회 => 해시 태그 : " + hashtagId);
        List<Store> stores = hashtagService.findStoresByMostSavedHashtags(hashtagId);
        return new ResponseEntity<>(stores, HttpStatus.ACCEPTED);
    }
}