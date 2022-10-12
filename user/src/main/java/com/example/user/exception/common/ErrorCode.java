package com.example.user.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404,"USER-001", "존재하지 않는 회원입니다."),
    USERNAME_DUPLICATION(400,"USER-002","이미 사용중인 아이디입니다."),
    PASSWORD_CHECK(400,"USER-003","잘못된 비밀번호입니다."),
    ;

    ;

    private int status;
    private String code;
    private String message;
}
