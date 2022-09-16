package com.cpcl.order.exception;

import com.cpcl.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCode {

    ORDER_PRODUCT_NOT_FOUND("O001", "주문 상품이 존재하지 않습니다."),
    ORDER_MEMBER_NOT_FOUND("O002", "주문자가 존재하지 않습니다."),

    ORDER_NOT_FOUND("O003", "주문이 존재하지 않습니다.");

    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
