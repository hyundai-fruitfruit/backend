package com.hyundai.app.event.domain;

import com.hyundai.app.common.entity.BaseEntity;

/**
 * @author 엄상은
 * @since 2024/02/18
 * 회원이 참여한 이벤트 엔티티
 */
public class MemberEvent extends BaseEntity {
    private int id;
    private int eventId;
    private String memberId;

    public MemberEvent(int eventId, String memberId) {
        this.eventId = eventId;
        this.memberId = memberId;
    }

    public static MemberEvent of(int eventId, String memberId) {
        return new MemberEvent(eventId, memberId);
    }
}
