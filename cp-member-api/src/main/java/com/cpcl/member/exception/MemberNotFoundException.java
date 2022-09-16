package com.cpcl.member.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
