package com.hyundai.app.coupon.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.coupon.domain.Coupon;
import com.hyundai.app.coupon.dto.CouponUsageRateDto;
import com.hyundai.app.coupon.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/20
 * 어드민용 쿠폰 컨트롤러
 */
@Api("어드민용 쿠폰 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/coupons")
@RestController
public class AdminCouponController {

    private final CouponService couponService;

    /**
     * @author 엄상은
     * @since 2024/02/20
     * 어드민용 해당 지점 쿠폰 전체 조회 API
     */
    @ApiOperation("어드민용 해당 지점 쿠폰 전체 조회 API")
    @GetMapping
    public AdventureOfHeendyResponse<List<Coupon>> findCouponList(@RequestParam int storeId) {
        return AdventureOfHeendyResponse.success("쿠폰 목록을 가져왔습니다.", couponService.findCouponList(storeId));
    }

    /**
     * @author 최성혁
     * @since 2024/03/04
     * 쿠폰 사용률 통계 조회 API
     */
    @ApiOperation("쿠폰 사용률 통계 조회 API")
    @GetMapping("/statistics")
    public AdventureOfHeendyResponse<List<CouponUsageRateDto>> findCouponUsageRates() {
        return AdventureOfHeendyResponse.success("쿠폰 사용률 통계를 가져왔습니다.", couponService.findCouponUsageRates());
    }
}
