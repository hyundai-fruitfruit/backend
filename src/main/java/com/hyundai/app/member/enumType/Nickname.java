package com.hyundai.app.member.enumType;

import lombok.Getter;

import java.util.Random;

/**
 * @author 황수영
 * @since 2024/02/12
 * 가입 시 발급되는 랜덤 닉네임
 */
@Getter
public enum Nickname {

    DESCRIPTORS("용감한 ", "야심찬 ", "당당한 ", "귀여운 "),
    NAME("흰디");

    private final String[] words;
    private static final Random random = new Random();

    Nickname(String... words) {
        this.words = words;
    }

    public static String getRandomNickname() {
        StringBuilder randomNickname = new StringBuilder();
        return randomNickname.append(getRandomWord(DESCRIPTORS.words))
                .append(getRandomWord(NAME.words))
                .toString();
    }

    private static String getRandomWord(String[] words)  {
        int index = random.nextInt(words.length);
        return words[index];
    }
}