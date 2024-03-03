package com.hyundai.app.coupon.mapper;

import com.hyundai.app.coupon.domain.Coupon;
import com.hyundai.app.coupon.dto.CouponUsageRateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/20
 * 쿠폰 관련 Mapper
 */
@Mapper
public interface CouponMapper {
    List<Coupon> findCouponList(int storeId);

    Coupon findById(int couponId);

    List<Coupon> findMemberCouponList(String memberId);

    List<CouponUsageRateDto> findCouponUsageRates();
}
