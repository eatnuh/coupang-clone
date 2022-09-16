package com.cpcl.security.exception;

import com.cpcl.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    METHOD_NOT_SUPPORTED("A001", "LOGIN 요청 HTTP METHOD = POST"),
    LOGIN_REQUEST_INVALID("A002", "잘못된 로그인 요청 형식"),
    LOGIN_MEMBER_NOT_FOUND("A003", "로그인 요청에 해당하는 멤버를 찾지 못했습니다."),
    UNAUTHORIZED("A004", "허용되지 않는 접근");

    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
