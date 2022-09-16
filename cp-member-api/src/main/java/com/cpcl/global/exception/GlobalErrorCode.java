package com.cpcl.global.exception;

import com.cpcl.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    INVALID_INPUT_VALUE("G001", "잘못된 입력입니다."),
    INVALID_TYPE_VALUE("G002", "잘못된 타입입니다."),
    METHOD_NOT_ALLOWED("G003", "허용되지 않는 HTTP METHOD"),
    UNKNOWN_EXCEPTION("G004", "알려지지 않은 Exception 발생");

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
