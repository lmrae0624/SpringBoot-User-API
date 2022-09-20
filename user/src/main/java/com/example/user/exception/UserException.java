package com.example.user.exception;

import com.example.user.exception.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException {
    private final ErrorCode errorCode;
}
