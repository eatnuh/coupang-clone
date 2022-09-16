package com.cpcl.product.dto;

import com.cpcl.product.Product;
import lombok.*;

public class ProductItem {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private Long id;
        private String name;
        private Integer price;

        public static Response fromEntity(Product product) {
            return Response.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
        }

    }
}
