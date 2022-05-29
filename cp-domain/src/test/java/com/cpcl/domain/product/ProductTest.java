package com.cpcl.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 도메인 테스트")
class ProductTest {

    @Test
    @DisplayName("상품 생성 테스트")
    void createProduct() {
        //given
        //when
        String name = "A Product";
        String content = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Integer price = 7000;
        String brand = "ABC corp";
        ProductStatus productStatus = ProductStatus.SALE;
        ProductCategory productCategory = ProductCategory.DIGITAL_VIDEO_PROJECTOR;

        Product product = Product.builder()
                .name(name)
                .content(content)
                .price(price)
                .brand(brand)
                .productStatus(productStatus)
                .productCategory(productCategory)
                .build();

        //then
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getContent()).isEqualTo(content);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getBrand()).isEqualTo(brand);
        assertThat(product.getProductStatus()).isEqualTo(productStatus);
        assertThat(product.getProductCategory()).isEqualTo(productCategory);
    }
}