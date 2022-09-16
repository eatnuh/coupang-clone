package com.cpcl.order.dto;

import com.cpcl.order.Order;
import lombok.*;

public class OrderDetail {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private Long id;
        private Integer price;
        private Integer discountPrice;
        private Integer shippingFee;
        private Integer finalPrice;
        private String productName;
        private Integer quantity;


        public static Response fromEntity(Order order) {
            return Response.builder()
                    .id(order.getId())
                    .price(order.getPurchase().getPrice())
                    .discountPrice(order.getPurchase().getDiscountPrice())
                    .shippingFee(order.getPurchase().getShippingFee())
                    .finalPrice(order.getPurchase().getFinalPrice())
                    .productName(order.getPurchase().getProductName())
                    .quantity(order.getPurchase().getQuantity())
                    .build();
        }

    }
}
