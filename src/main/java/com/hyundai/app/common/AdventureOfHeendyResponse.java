package com.hyundai.app.common;

import lombok.Getter;

/**
 * @author 엄상은
 * @since 2024/02/08
 * 공통 응답 객체 설정
 */
@Getter
public class AdventureOfHeendyResponse<T> {
    private boolean success;
    private String message;
    private T data;

    private AdventureOfHeendyResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> AdventureOfHeendyResponse<T> success(String message, T data) {
        return new AdventureOfHeendyResponse<T>(true, message, data);
    }

    public static <T> AdventureOfHeendyResponse<T> fail(String message) {
        return new AdventureOfHeendyResponse<T>(false, message, null);
    }
}
