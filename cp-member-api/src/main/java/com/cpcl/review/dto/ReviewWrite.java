package com.cpcl.review.dto;

import com.cpcl.member.Member;
import com.cpcl.member.dto.MemberSignup;
import com.cpcl.order.Order;
import com.cpcl.product.Product;
import com.cpcl.review.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReviewWrite {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        @NotBlank(message = "평점은 필수입니다.")
        private Integer rating;

        @NotBlank(message = "내용은 필수입니다.")
        @Size(max = 500, message = "내용은 500자 까지입니다.")
        private String content;

        public Review toEntity(Member member, Product product, Order order) {
            return Review.builder()
                    .rating(rating)
                    .content(content)
                    .member(member)
                    .product(product)
                    .order(order)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long reviewIdId;

        public static Response fromEntity(Review review) {
            return new Response(review.getId());
        }
    }
}
