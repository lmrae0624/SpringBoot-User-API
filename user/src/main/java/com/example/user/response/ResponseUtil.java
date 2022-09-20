package com.example.user.response;

import com.example.user.dto.ApiResponse;
import com.example.user.exception.common.ErrorResponse;
import com.example.user.exception.common.ErrorCode;

public class ResponseUtil<T> {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, data,null);
    }

    public static ApiResponse<?> error(ErrorCode errorCode){
        return new ApiResponse<>(errorCode.getStatus(), null, new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }

}
