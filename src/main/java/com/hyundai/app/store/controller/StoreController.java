package com.hyundai.app.store.controller;

import com.hyundai.app.store.dto.StoreResDto;
import com.hyundai.app.store.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author 황수영
 * @since 2024/02/13
 * 매장 관련 기능 컨트롤러
 */
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
}
