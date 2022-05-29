package com.cpcl.domain.product;

import com.cpcl.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
