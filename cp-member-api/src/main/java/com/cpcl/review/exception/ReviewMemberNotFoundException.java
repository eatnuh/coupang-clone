package com.cpcl.review.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class ReviewMemberNotFoundException extends BusinessException {
    public ReviewMemberNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ReviewMemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
