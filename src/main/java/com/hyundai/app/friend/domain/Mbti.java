package com.hyundai.app.friend.domain;

import lombok.Getter;

/**
 * @author 엄상은
 * @since 2024/02/13
 * MBTI 엔티티
 */
@Getter
public class Mbti {
    private String id;
    private int energy;
    private int information;
    private int decision;
    private int lifestyle;
}
