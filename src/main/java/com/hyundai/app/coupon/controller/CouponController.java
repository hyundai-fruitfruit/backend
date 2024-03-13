package com.hyundai.app.coupon.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.coupon.domain.Coupon;
import com.hyundai.app.coupon.service.CouponService;
import com.hyundai.app.security.methodparam.MemberId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/23
 * 사용자용 쿠폰 컨트롤러
 */
@Api("사용자용 쿠폰 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupons")
@RestController
public class CouponController {
    private final CouponService couponService;

    /**
     * @author 엄상은
     * @since 2024/02/23
     * 사용자용 쿠폰 전체 조회 API
     */
    @ApiOperation("사용자용 쿠폰 전체 조회 API")
    @GetMapping
    public AdventureOfHeendyResponse<List<Coupon>> findCouponList(@ApiIgnore @MemberId String memberId) {
        return AdventureOfHeendyResponse.success("사용자의 쿠폰 목록을 가져왔습니다.", couponService.findMemberCouponList(memberId));
    }

    /**
     * @author 엄상은
     * @since 2024/03/05
     * 사용자용 쿠폰 사용 API
     */
    @ApiOperation("사용자용 쿠폰 사용 API")
    @PostMapping("/{couponId}/use")
    public AdventureOfHeendyResponse<Void> useCoupon(@ApiIgnore @MemberId String memberId,
                                                     @PathVariable final int couponId) {
        return AdventureOfHeendyResponse.success("쿠폰을 사용했습니다.", couponService.useCoupon(memberId, couponId));
    }
}
