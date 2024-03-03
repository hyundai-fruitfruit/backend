package com.hyundai.app.member.domain;

import com.hyundai.app.common.entity.BaseEntity;
import com.hyundai.app.member.enumType.Role;
import lombok.*;

/**
 * @author 황수영
 * @since 2024/02/12
 * 회원 도메인
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    private String id;
    private String email;
    private String nickname;
    private Role role;
    private String refreshToken;
    private String deviceToken;
    private String oauthId;
    private String imgUrl;
    private String qrUrl;

    /**
     * @author 엄상은
     * @since 2024/02/26
     * 회원 QR 업데이트
     */
    public void updateQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }
}
