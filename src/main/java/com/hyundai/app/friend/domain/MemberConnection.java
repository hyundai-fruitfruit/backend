package com.hyundai.app.friend.domain;

import com.hyundai.app.common.entity.BaseEntity;
import lombok.*;

/**
 * @author 엄상은
 * @since 2024/02/09
 * 친구 관계 관련 엔티티
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberConnection extends BaseEntity {
    private int id;
    private int fromMemberId;
    private int toMemberId;
    private int isDeleted;
    private String mbtiId;

    @Builder
    public MemberConnection(int fromMemberId, int toMemberId) {
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
    }

    @Builder
    public MemberConnection(int fromMemberId, int toMemberId, String mbtiId) {
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.mbtiId = mbtiId;
    }
}