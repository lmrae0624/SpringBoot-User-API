package com.example.user.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404,"USER-001", "존재하지 않는 회원입니다."),
    USERNAME_DUPLICATION(400,"USER-002","이미 사용중인 아이디입니다."),
    PASSWORD_CHECK(400,"USER-003","잘못된 비밀번호입니다."),

    INVALID_SIGNATURE_TOKEN(403,"TOKEN-001","시그니처 검증에 실패한 토큰입니다."),
    WRONG_TYPE_TOKEN(403,"TOKEN-002","잘못된 토큰입니다."),
    EXPIRED_TOKEN(403,"TOKEN-003","만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(403,"TOKEN-004","지원하지 않는 토큰입니다.")
    ;

    private int status;
    private String code;
    private String message;
}
