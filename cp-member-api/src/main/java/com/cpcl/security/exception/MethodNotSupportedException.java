package com.cpcl.security.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class MethodNotSupportedException extends BusinessException {
    public MethodNotSupportedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
