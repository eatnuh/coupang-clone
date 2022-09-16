package com.cpcl.config;

import com.cpcl.member.Member;
import com.cpcl.member.repository.MemberRepository;
import com.cpcl.order.repository.OrderRepository;
import com.cpcl.product.Product;
import com.cpcl.product.repository.ProductRepository;
import com.cpcl.review.repository.ReviewRepository;
import com.cpcl.seller.repository.SellerRepository;
import com.cpcl.product.type.ProductCategory;
import com.cpcl.product.type.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitConfig {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final SellerRepository sellerRepository;

    @Bean
    public void init() {
        memberRepository.save(Member.builder()
                .email("abc@abc.com")
                .password("1234")
                .name("mike")
                .phone("010-1234-1234")
                .build());
        productRepository.save(Product.builder()
                .name("남자옷")
                .content("남자옷이에요")
                .price(10000)
                .brand("A사")
                .productStatus(ProductStatus.SALE)
                .productCategory(ProductCategory.FASHION_MEN_CLOTHING)
                .build());
        productRepository.save(Product.builder()
                .name("여자옷")
                .content("여자옷이에요")
                .price(10000)
                .brand("B사")
                .productStatus(ProductStatus.SALE)
                .productCategory(ProductCategory.FASHION_WOMEN_CLOTHING)
                .build());
        productRepository.save(Product.builder()
                .name("가전제품")
                .content("가전제품이에요")
                .price(10000)
                .brand("C사")
                .productStatus(ProductStatus.SALE)
                .productCategory(ProductCategory.DIGITAL_VIDEO_TV)
                .build());
        productRepository.save(Product.builder()
                .name("여자반지")
                .content("여자반지에요")
                .price(35000)
                .brand("D사")
                .productStatus(ProductStatus.SALE)
                .productCategory(ProductCategory.FASHION_WOMEN_ACCESSORIES)
                .build());
    }
}
