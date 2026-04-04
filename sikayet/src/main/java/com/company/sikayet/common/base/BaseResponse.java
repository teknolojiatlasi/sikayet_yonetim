package com.company.sikayet.common.base;

public record BaseResponse<T>(boolean success, T data, String message) {

    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(true, data, message);
    }
}
