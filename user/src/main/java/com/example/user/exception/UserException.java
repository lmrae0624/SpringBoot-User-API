package com.example.user.exception;

import com.example.user.exception.common.ErrorCode;
import lombok.Getter;

@Getter
//@AllArgsConstructor
public class UserException extends RuntimeException {

    private ErrorCode errorCode;

    public UserException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}

