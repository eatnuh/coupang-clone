package com.cpcl.security.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class LoginRequestInvalidException extends BusinessException {
    public LoginRequestInvalidException(ErrorCode errorCode) {
        super(errorCode);
    }
}
