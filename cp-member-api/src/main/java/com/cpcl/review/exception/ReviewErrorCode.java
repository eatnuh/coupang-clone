package com.cpcl.review.exception;

import com.cpcl.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {

    REVIEW_NOT_FOUND("R001", "리뷰가 존재하지 않습니다."),
    REVIEW_MEMBER_NOT_FOUND("R002", "리뷰 멤버가 존재하지 않습니다."),
    REVIEW_ORDER_NOT_FOUND("R003", "리뷰 주문이 존재하지 않습니다."),
    REVIEW_ORDER_NOT_COMPLETED("R004", "주문이 완료되지 않았습니다.");

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
