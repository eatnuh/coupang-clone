package com.cpcl.review.repository;

import com.cpcl.product.Product;
import com.cpcl.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewSearchRepository {
}
