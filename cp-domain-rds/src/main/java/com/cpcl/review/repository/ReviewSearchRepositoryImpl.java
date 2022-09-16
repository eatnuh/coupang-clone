package com.cpcl.review.repository;

import com.cpcl.review.Review;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static com.cpcl.review.QReview.review;

public class ReviewSearchRepositoryImpl extends QuerydslRepositorySupport implements ReviewSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ReviewSearchRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Review.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Review> searchReview(Long productId, Pageable pageable) {
        JPAQuery<Review> jpaQuery = jpaQueryFactory.selectFrom(review)
                .where(
                        review.product.id.eq(productId)
                );

        List<Review> result = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
        return result;
    }
}
