package com.example.user.dto;

import com.example.user.exception.common.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse<T> {
    private final boolean success;
    private final T response;
    private final ErrorResponse errorResponse;

    public ResultResponse(boolean success, T response, ErrorResponse errorResponse) {
        this.success = success;
        this.response = response;
        this.errorResponse = errorResponse;
    }
}
