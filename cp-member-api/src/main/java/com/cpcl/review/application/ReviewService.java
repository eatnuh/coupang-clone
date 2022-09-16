package com.cpcl.review.application;

import com.cpcl.exception.ErrorCode;
import com.cpcl.member.Member;
import com.cpcl.member.repository.MemberRepository;
import com.cpcl.order.Order;
import com.cpcl.order.repository.OrderRepository;
import com.cpcl.product.Product;
import com.cpcl.product.repository.ProductRepository;
import com.cpcl.review.api.search.ReviewSearchCondition;
import com.cpcl.review.dto.ReviewDetail;
import com.cpcl.review.dto.ReviewItem;
import com.cpcl.review.dto.ReviewWrite;
import com.cpcl.review.exception.*;
import com.cpcl.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<ReviewItem.Response> getReviewItems(Long productId, ReviewSearchCondition reviewSearchCondition) {
        return reviewRepository.searchReview(productId, reviewSearchCondition.getPageRequest()).stream()
                .map(ReviewItem.Response::fromEntity)
                .collect(Collectors.toList());
    }

    public ReviewDetail.Response getReviewDetail(Long reviewId) {
        return ReviewDetail.Response.fromEntity(reviewRepository.findById(reviewId).orElseThrow(
                () -> new ReviewNotFoundException(
                        ReviewErrorCode.REVIEW_NOT_FOUND,
                        String.format("getReviewDetail : reviewId가 %d인 리뷰가 없습니다.", reviewId)
                )
        ));
    }

    @Transactional
    public ReviewWrite.Response writeReview(ReviewWrite.Request request, Long orderId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new ReviewMemberNotFoundException(
                                ReviewErrorCode.REVIEW_MEMBER_NOT_FOUND,
                                String.format("writeReview : memberId가 %d인 멤버가 없습니다.", memberId)
                                )
                );

        Order order = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new ReviewOrderNotFoundException(
                                ReviewErrorCode.REVIEW_ORDER_NOT_FOUND,
                                String.format("writeReview : orderId가 %d인 주문이 없습니다.", orderId)
                        )
                );

        if(order.isNotCompleted()) {
            throw new ReviewOrderNotCompletedException(
                    ReviewErrorCode.REVIEW_ORDER_NOT_COMPLETED,
                    String.format("writeReview : orderId가 %d인 주문이 배송완료 상태가 아닙니다.", orderId)
            );
        }

        Product product = order.getProduct();

        return ReviewWrite.Response.fromEntity(
                        reviewRepository.save(request.toEntity(member, product, order))
        );

    }
}
