package com.cpcl.domain.order;

import com.cpcl.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
