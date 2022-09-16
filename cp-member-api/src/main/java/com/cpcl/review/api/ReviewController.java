package com.cpcl.review.api;

import com.cpcl.member.Member;
import com.cpcl.review.api.search.ReviewSearchCondition;
import com.cpcl.review.api.search.ReviewSearchParams;
import com.cpcl.review.application.ReviewService;
import com.cpcl.review.dto.ReviewDetail;
import com.cpcl.review.dto.ReviewItem;
import com.cpcl.review.dto.ReviewWrite;
import com.cpcl.security.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/api/product/{productId}/review/search")
    public List<ReviewItem.Response> getReviewItems(@PathVariable Long productId,
                                                    @ReviewSearchParams ReviewSearchCondition reviewSearchCondition) {
        return reviewService.getReviewItems(productId, reviewSearchCondition);

    }

    @GetMapping("/api/review/{reviewId}")
    public ReviewDetail.Response getReviewDetail(@PathVariable Long reviewId) {
        return reviewService.getReviewDetail(reviewId);
    }

    @PostMapping("/api/order/{orderId}/review")
    public ReviewWrite.Response writeReview(@RequestBody ReviewWrite.Request request,
                                   @PathVariable Long orderId,
                                   @AuthMember Member member) {
        return reviewService.writeReview(request, orderId, member.getId());
    }
}
