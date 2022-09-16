package com.cpcl.order.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class OrderMemberNotFoundException extends BusinessException {

    public OrderMemberNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public OrderMemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
