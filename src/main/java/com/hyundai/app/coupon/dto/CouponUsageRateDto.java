package com.hyundai.app.coupon.dto;

import com.hyundai.app.coupon.enumType.CouponType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 최성혁
 * @since 2024/03/04
 * 쿠폰사용비율 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponUsageRateDto {
    private CouponType couponType;
    private int totalIssued;
    private int totalUsed;
    private double usageRate;

    public void setUsageRate(double usageRate) {
        BigDecimal bd = new BigDecimal(usageRate).setScale(2, RoundingMode.HALF_UP);
        this.usageRate = bd.doubleValue();
    }
}
