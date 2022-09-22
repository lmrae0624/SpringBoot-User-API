package com.example.user.exception;

import com.example.user.exception.common.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ErrorResponse ExceptionController(Exception e) {
        return new ErrorResponse("501", "테스트 커스텀 예외 입니다.");
    }

    // new ApiError(errorCode.getCode(), errorCode.getMessage())
}
