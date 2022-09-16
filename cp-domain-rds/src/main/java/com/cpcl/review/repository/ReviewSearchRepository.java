package com.cpcl.review.repository;

import com.cpcl.product.Product;
import com.cpcl.review.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewSearchRepository {

    List<Review> searchReview(Long productId, Pageable pageable);
}
