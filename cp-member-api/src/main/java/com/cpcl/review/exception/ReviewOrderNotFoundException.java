package com.cpcl.review.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class ReviewOrderNotFoundException extends BusinessException {
    public ReviewOrderNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ReviewOrderNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
