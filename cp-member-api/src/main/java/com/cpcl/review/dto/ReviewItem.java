package com.cpcl.review.dto;

import com.cpcl.order.Order;
import com.cpcl.order.dto.OrderItem;
import com.cpcl.review.Review;
import lombok.*;

public class ReviewItem {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private Long id;
        private String reviewer;
        private Integer rating;

        public static Response fromEntity(Review review) {
            return Response.builder()
                    .id(review.getId())
                    .reviewer(review.getMember().getName())
                    .rating(review.getRating())
                    .build();
        }

    }
}
