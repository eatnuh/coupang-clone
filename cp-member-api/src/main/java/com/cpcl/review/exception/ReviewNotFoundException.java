package com.cpcl.review.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class ReviewNotFoundException extends BusinessException {
    public ReviewNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ReviewNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
