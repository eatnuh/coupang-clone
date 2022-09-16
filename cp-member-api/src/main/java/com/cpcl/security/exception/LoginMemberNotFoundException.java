package com.cpcl.security.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class LoginMemberNotFoundException extends BusinessException {
    public LoginMemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
