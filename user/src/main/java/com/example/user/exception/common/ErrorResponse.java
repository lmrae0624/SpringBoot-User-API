package com.example.user.exception.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private final String code;
    private final String message;

    public ErrorResponse(String code , String message) {
        this.code = code;
        this.message = message;
    }

}
