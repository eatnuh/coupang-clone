package com.cpcl.product.dto;

import com.cpcl.product.Product;
import com.cpcl.product.type.ProductCategory;
import com.cpcl.product.type.ProductStatus;
import lombok.*;

public class ProductDetail {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private Long id;
        private String name;
        private String content;
        private Integer price;
        private String brand;
        private ProductStatus productStatus;
        private ProductCategory productCategory;
        private String companyName;

        public static Response fromEntity(Product product) {
            return Response.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .content(product.getContent())
                    .price(product.getPrice())
                    .brand(product.getBrand())
                    .productStatus(product.getProductStatus())
                    .productCategory(product.getProductCategory())
                    .companyName(product.getSeller().getCompany().getName())
                    .build();
        }

    }
}
