package com.example.user.response;

import com.example.user.dto.ApiResponse;

public class ResponseUtil<T> {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, data);
    }

}
