package com.cpcl.order.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class OrderProductNotFoundException extends BusinessException {

    public OrderProductNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public OrderProductNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
