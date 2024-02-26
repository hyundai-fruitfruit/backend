package com.hyundai.app.coupon.service;

import com.hyundai.app.coupon.domain.Coupon;
import com.hyundai.app.coupon.enumType.CouponType;
import com.hyundai.app.coupon.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 엄상은
 * @since 2024/02/20
 * 쿠폰 서비스
 */
@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponMapper couponMapper;
    public List<Coupon> findCouponList(int storeId) {
        List<Coupon> couponList = couponMapper.findCouponList(storeId);
        for (Coupon coupon: couponList) {
            CouponType couponType = coupon.getCouponType();
            String description = couponType.getDescription(coupon);
            coupon.updateContent(description);
        }
        return couponList;
    }

    public List<Coupon> findMemberCouponList(String memberId) {
        List<Coupon> couponList = couponMapper.findMemberCouponList(memberId);
        for (Coupon coupon: couponList) {
            CouponType couponType = coupon.getCouponType();
            String description = couponType.getDescription(coupon);
            coupon.updateContent(description);
        }
        return couponList;
    }
}
