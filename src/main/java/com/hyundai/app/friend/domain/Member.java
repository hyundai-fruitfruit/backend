package com.hyundai.app.friend.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author : 엄상은
 * @since : 2024/02/09
 * 회원 엔티티
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    private int id;
    private int characterId;
    private int exp;
    private int isDeleted;
    private String nickname;
    private String imgUrl;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private String gender;
    private Date birth;
    private int grade;
    private String selfMbtiId;
    private String perceivedMbtiId;
}
