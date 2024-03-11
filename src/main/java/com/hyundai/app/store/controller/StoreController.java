package com.hyundai.app.store.controller;

import com.hyundai.app.security.methodparam.MemberId;
import com.hyundai.app.store.dto.ReviewReqDto;
import com.hyundai.app.store.dto.StoreResDto;
import com.hyundai.app.store.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 관련 기능 컨트롤러
 */
@Log4j
@Api("매장 관련 API")
@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    @Autowired
    @Qualifier("storeServiceImpl")
    private StoreService storeService;

    /**
     * @author 황수영
     * @since 2024/02/13
     * 매장 상세 조회
     */
    @GetMapping("/{storeId}")
    @ApiOperation("매장 상세 조회 API")
    public ResponseEntity<StoreResDto> getStoreDetail(
            @PathVariable int storeId) {
        StoreResDto storeResDto = storeService.getStoreDetail(storeId);
        return new ResponseEntity<>(storeResDto, HttpStatus.ACCEPTED);
    }

    /**
     * @author 황수영
     * @since 2024/02/14
     * 매장 리뷰 작성
     */
    @PostMapping(value = "/{storeId}/reviews", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation("매장 리뷰 작성 API")
    public ResponseEntity<Void> createReview(
            @PathVariable int storeId,
            @RequestPart(value = "reviewReqDto") ReviewReqDto reviewReqDto,
            @RequestPart(value = "imageList", required = false) List<MultipartFile> imageList,
            @ApiIgnore @MemberId String memberId
    ) {
        log.debug("매장 리뷰 작성 API" + reviewReqDto.toString());
        storeService.createReview(storeId, memberId, reviewReqDto, imageList);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}