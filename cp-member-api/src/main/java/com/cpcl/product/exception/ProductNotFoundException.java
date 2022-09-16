package com.cpcl.product.exception;

import com.cpcl.exception.BusinessException;
import com.cpcl.exception.ErrorCode;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ProductNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
