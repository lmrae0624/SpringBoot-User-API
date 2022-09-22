package com.example.user.exception;

import lombok.Getter;

@Getter
//@AllArgsConstructor
public class UserException extends RuntimeException {
    //선택한 생성자가 자동으로 생성되었습니다. 각 생성자는 RuntimeException생성자를 호출하는 Super()함수를 호출하고 있습니다.
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
