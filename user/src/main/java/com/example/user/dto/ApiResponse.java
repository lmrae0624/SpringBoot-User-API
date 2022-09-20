package com.example.user.dto;

import com.example.user.exception.common.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private final int status;
    private final T data;
    private final ErrorResponse errorResponse;

    public ApiResponse(int status , T data, ErrorResponse errorResponse) {
        this.status = status;
        this.data = data;
        this.errorResponse = errorResponse;
    }

}
