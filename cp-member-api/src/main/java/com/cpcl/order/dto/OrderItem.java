package com.cpcl.order.dto;

import com.cpcl.order.Order;
import com.cpcl.product.Product;
import com.cpcl.product.dto.ProductItem;
import lombok.*;

public class OrderItem {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private Long id;
        private String productName;
        private Integer price;
        private Integer quantity;

        public static Response fromEntity(Order order) {
            return Response.builder()
                    .id(order.getId())
                    .productName(order.getPurchase().getProductName())
                    .quantity(order.getPurchase().getQuantity())
                    .build();
        }

    }
}
