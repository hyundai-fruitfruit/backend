package com.hyundai.app.coupon.mapper;

import com.hyundai.app.coupon.domain.MemberCoupon;
import com.hyundai.app.coupon.dto.CouponUsageRateDto;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/22
 * 쿠폰을 가진 사용자 매퍼
 */
public interface MemberCouponMapper {
    void saveMemberCoupon(MemberCoupon memberCoupon);

}
