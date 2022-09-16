package com.cpcl.product.repository;

import com.cpcl.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearchRepository {
}
