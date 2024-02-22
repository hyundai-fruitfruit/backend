package com.hyundai.app.event.dto;

import com.hyundai.app.coupon.domain.Coupon;
import lombok.Getter;

/**
 * @author 엄상은
 * @since 2024/02/22
 * 이벤트 참여 응답 DTO
 */
@Getter
public class EventParticipateResDto {
    EventDetailResDto eventDetailResDto;
    Coupon coupon;

    public EventParticipateResDto(EventDetailResDto eventDetailResDto, Coupon coupon) {
        this.eventDetailResDto = eventDetailResDto;
        this.coupon = coupon;
    }

    public static EventParticipateResDto from(EventDetailResDto eventDetailResDto, Coupon coupon) {
        return new EventParticipateResDto(eventDetailResDto, coupon);
    }

    public static EventParticipateResDto of(EventDetailResDto eventDetailResDto) {
        return new EventParticipateResDto(eventDetailResDto, null);
    }

    public void updateCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
