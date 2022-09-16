package com.cpcl.review.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class ReviewOrderNotCompletedException extends BusinessException {
    public ReviewOrderNotCompletedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ReviewOrderNotCompletedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
