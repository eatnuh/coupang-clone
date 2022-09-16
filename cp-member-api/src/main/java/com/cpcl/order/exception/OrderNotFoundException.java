package com.cpcl.order.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public OrderNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
