package com.cpcl.product.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {
    WAITING_FOR_SALE("판매대기"),
    SALE("판매중"),
    SOLD_OUT("품절"),
    END_SALE("판매종료");

    private final String description;
}
