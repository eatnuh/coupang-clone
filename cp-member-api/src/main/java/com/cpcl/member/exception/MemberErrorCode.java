package com.cpcl.member.exception;

import com.cpcl.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    MEMBER_NOT_FOUND("M001", "해당 멤버를 찾을 수 없습니다."),
    EMAIL_DUPLICATION("M002", "이메일이 중복입니다.");

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
