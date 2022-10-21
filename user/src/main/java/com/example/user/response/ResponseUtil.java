package com.example.user.response;

import com.example.user.dto.ApiResponse;
import com.example.user.exception.UserException;
import com.example.user.exception.common.ErrorResponse;

public class ResponseUtil<T> {

    // 성공 시
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, data);
    }

    //실패 시
    public static ErrorResponse fail(UserException e) {
        return new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }


}
