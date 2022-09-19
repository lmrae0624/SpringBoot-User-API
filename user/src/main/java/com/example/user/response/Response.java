package com.example.user.response;

import com.example.user.dto.ExceptionResponse;
import com.example.user.dto.ResultResponse;

public class Response {
    public static <T> ResultResponse<T> success(T response) {
        return new ResultResponse<>(true, response, null);
    }

    public static ResultResponse<?> error(String message, int status){
        return new ResultResponse<>(false, null, new ExceptionResponse(message, status));
    }
}
