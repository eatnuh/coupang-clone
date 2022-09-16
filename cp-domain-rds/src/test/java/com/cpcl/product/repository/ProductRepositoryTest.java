package com.cpcl.product.repository;

import com.cpcl.RepositoryTest;
import com.cpcl.product.Product;
import com.cpcl.product.type.ProductCategory;
import com.cpcl.product.type.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("ProductRepository 테스트")
class ProductRepositoryTest extends RepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    private Product givenProduct;

    @BeforeEach
    void setUp() {
        givenProduct = Product.builder()
                .name("남자옷")
                .content("남자옷이에요")
                .price(10000)
                .brand("A사")
                .productStatus(ProductStatus.SALE)
                .productCategory(ProductCategory.FASHION_MEN_CLOTHING)
                .build();
    }

    @DisplayName("Product 저장")
    @Test
    void save() {
        //given
        //when
        Product savedProduct = productRepository.save(givenProduct);
        //then
        assertThat(savedProduct.getId()).isNotNull();
    }

    @DisplayName("Product id 조회")
    @Test
    void findById() {
        //given
        Product savedProduct = productRepository.save(givenProduct);
        //when
        Optional<Product> findProduct = productRepository.findById(savedProduct.getId());
        //then
        assertThat(findProduct).containsSame(savedProduct);
    }

    @DisplayName("Product Search 조건 조회")
    @Test
    void searchProduct() {
        //given
        productRepository.save(givenProduct);
        //when
        List<Product> findProductList =
                productRepository.searchProduct(
                        givenProduct.getProductCategory()
                        , PageRequest.of(0, 10));
        //then
        assertThat(findProductList).isNotEmpty();
        assertThat(findProductList.get(0)).isEqualTo(givenProduct);
    }

}