package com.hyundai.app.coupon.controller;

import com.hyundai.app.common.AdventureOfHeendyResponse;
import com.hyundai.app.coupon.domain.Coupon;
import com.hyundai.app.coupon.service.CouponService;
import com.hyundai.app.security.methodparam.MemberId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @ApiOperation("사용자용 쿠폰 전체 조회 API")
    @GetMapping
    public AdventureOfHeendyResponse<List<Coupon>> findCouponList(@ApiIgnore @MemberId Integer memberId) {
        return AdventureOfHeendyResponse.success("사용자의 쿠폰 목록을 가져왔습니다.", couponService.findMemberCouponList(memberId));
    }
}
