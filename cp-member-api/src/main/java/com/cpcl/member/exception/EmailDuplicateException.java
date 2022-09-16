package com.cpcl.member.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class EmailDuplicateException extends BusinessException {

    public EmailDuplicateException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EmailDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
