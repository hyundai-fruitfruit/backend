package com.hyundai.app.coupon.enumType;

import com.hyundai.app.coupon.domain.Coupon;

import java.util.function.Function;

/**
 * @author 엄상은
 * @since 2024/02/20
 * 쿠폰 종류
 */
public enum CouponType {
    PERCENT(coupon -> "주문 금액 " + coupon.getMinimumAmount() + "원 이상 구매시 " + coupon.getDiscountRate() + "% 할인"),
    AMOUNT(coupon -> "주문 금액 " + coupon.getMinimumAmount() + "원 이상 구매시 " + coupon.getDiscountAmount() + "원 할인"),
    FREE(coupon -> "무료");

    private final Function<Coupon, String> description;

    CouponType(Function<Coupon, String> description) {
        this.description = description;
    }

    public String getDescription(Coupon coupon) {
        return description.apply(coupon);
    }
}
