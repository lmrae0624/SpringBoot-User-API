package com.example.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private final int status;
    private final T data;
   // private final ErrorResponse errorResponse;

    public ApiResponse(int status , T data) {
        this.status = status;
        this.data = data;
    }

}
