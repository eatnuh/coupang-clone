package com.cpcl.order.payment.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {
    CREDIT_CARD("신용카드"),
    PHONE("핸드폰 결제");

    private final String description;
}
