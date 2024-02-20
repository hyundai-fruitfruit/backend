package com.hyundai.app.coupon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyundai.app.coupon.enumType.CouponType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author 엄상은
 * @since 2024/02/20
 * 쿠폰 도메인
 */
@Getter
@NoArgsConstructor
public class Coupon {
    private int id;
    private int storeId;
    private int discountRate;
    private int discountAmount;
    private int minimumAmount;
    private CouponType couponType;
    private String content;
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishedAt;
}
