package com.hyundai.app.friend.domain;

import com.hyundai.app.common.entity.BaseEntity;

/**
 * @author 엄상은
 * @since 2024/02/13
 * 친구와 하는 게임 엔티티
 */
public class ConnectionGame extends BaseEntity {
    private int id;
    private int fromMemberId;
    private int toMemberId;
    private int winnerId;
    private int diceToMember;
    private int diceFromMember;
    private int isMatched;
    private int bettedExp;
    private int isValid;
}
