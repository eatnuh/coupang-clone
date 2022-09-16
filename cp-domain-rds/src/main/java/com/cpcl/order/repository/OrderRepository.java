package com.cpcl.order.repository;

import com.cpcl.member.Member;
import com.cpcl.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByMember(Member member);
}
