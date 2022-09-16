package com.cpcl.review;

import com.cpcl.member.Member;
import com.cpcl.product.Product;
import com.cpcl.review.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("리뷰 도메인 테스트")
class ReviewTest {

    @Test
    @DisplayName("리뷰 생성 테스트")
    void createReview() {
        //given
        //when
        Integer rating = 10;
        String content = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Member member = Member.builder()
                .email("abc@abc.com")
                .name("john")
                .build();
        Product product = Product.builder()
                .name("A product")
                .build();

        Review review = Review.builder()
                .rating(rating)
                .content(content)
                .member(member)
                .product(product)
                .build();

        //then
        Assertions.assertThat(review.getRating()).isEqualTo(rating);
        Assertions.assertThat(review.getContent()).isEqualTo(content);
        Assertions.assertThat(review.getMember()).isEqualTo(member);
        Assertions.assertThat(review.getProduct()).isEqualTo(product);
    }
}