package com.hyundai.app.coupon.domain;

import com.hyundai.app.common.entity.BaseEntity;

import java.time.LocalDate;

/**
 * @author 엄상은
 * @since 2024/02/22
 * 유저가 가지고 있는 쿠폰 엔티티
 */
public class MemberCoupon extends BaseEntity {
    private int id;
    private int memberId;
    private int couponId;
    private int isUsed;
    private String channelType;
    private LocalDate expiredAt;

    public MemberCoupon(int memberId, int couponId, String channelType) {
        this.memberId = memberId;
        this.couponId = couponId;
        this.channelType = channelType;
    }

    public static MemberCoupon of(int memberId, int couponId, String channelType) {
        return new MemberCoupon(memberId, couponId, channelType);
    }
}
