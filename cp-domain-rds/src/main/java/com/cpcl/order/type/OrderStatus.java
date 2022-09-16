package com.cpcl.order.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus  {
    COMPLETE_PAYMENT("결제완료"),
    PRODUCT_PREPARED("상품 준비중"),
    SHIPPING_START("배송 시작"),
    SHIPPING("배송중"),
    SHIPPING_COMPLETED("배송완료");

    private final String description;
}
