package com.example.user.security;

import com.example.user.exception.common.ErrorCode;

public class JwtTokenException extends RuntimeException{
    private final com.example.user.exception.common.ErrorCode ErrorCode;

    public JwtTokenException(ErrorCode ErrorCode) {
        super(ErrorCode.getMessage());
        this.ErrorCode = ErrorCode;
    }

    public ErrorCode getErrorCode() {
        return this.ErrorCode;
    }
}
