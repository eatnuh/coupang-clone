package com.cpcl.review.dto;

import com.cpcl.review.Review;
import lombok.*;

public class ReviewDetail {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private Long id;
        private String reviewer;
        private Integer rating;
        private String content;
        private String productName;


        public static Response fromEntity(Review review) {
            return Response.builder()
                    .id(review.getId())
                    .reviewer(review.getMember().getName())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .productName(review.getProduct().getName())
                    .build();
        }

    }
}
