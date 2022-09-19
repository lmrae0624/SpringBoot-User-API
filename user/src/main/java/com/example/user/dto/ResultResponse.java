package com.example.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse<T> {
    private final boolean success;
    private final T response;
    private final ExceptionResponse exceptionResponse;

    public ResultResponse(boolean success, T response, ExceptionResponse exceptionResponse) {
        this.success = success;
        this.response = response;
        this.exceptionResponse = exceptionResponse;
    }
}
