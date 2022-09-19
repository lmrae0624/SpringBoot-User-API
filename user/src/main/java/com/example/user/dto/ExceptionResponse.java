package com.example.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private final String message;
    private final int status;

    public ExceptionResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

}
