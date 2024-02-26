package com.hyundai.app.friend.domain;

import com.hyundai.app.common.entity.BaseEntity;
import lombok.*;

/**
 * @author 엄상은
 * @since 2024/02/09
 * 친구 관계 관련 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberConnection extends BaseEntity {
    private int id;
    private String fromMemberId;
    private String toMemberId;
    private int isDeleted;
    private String mbtiId;

    @Builder
    public MemberConnection(String fromMemberId, String toMemberId) {
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
    }

    @Builder
    public MemberConnection(String fromMemberId, String toMemberId, String mbtiId) {
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.mbtiId = mbtiId;
    }
}
