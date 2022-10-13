package com.example.user.exception;

import com.example.user.exception.common.ErrorCode;
import lombok.Getter;

@Getter
//@AllArgsConstructor
public class UserException extends RuntimeException {

    private final ErrorCode ErrorCode;

    public UserException(ErrorCode ErrorCode) {
        super(ErrorCode.getMessage());
        this.ErrorCode = ErrorCode;
    }

    public ErrorCode getErrorCode() {
        return this.ErrorCode;
    }
}
