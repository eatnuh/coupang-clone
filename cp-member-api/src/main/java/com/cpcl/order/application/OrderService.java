package com.cpcl.order.application;

import com.cpcl.member.Member;
import com.cpcl.member.repository.MemberRepository;
import com.cpcl.order.Order;
import com.cpcl.order.dto.OrderDetail;
import com.cpcl.order.dto.OrderItem;
import com.cpcl.order.dto.OrderRequest;
import com.cpcl.order.dto.OrderResponse;
import com.cpcl.order.exception.OrderErrorCode;
import com.cpcl.order.exception.OrderMemberNotFoundException;
import com.cpcl.order.exception.OrderNotFoundException;
import com.cpcl.order.exception.OrderProductNotFoundException;
import com.cpcl.order.repository.OrderRepository;
import com.cpcl.product.Product;
import com.cpcl.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final PaymentService paymentService;

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse proceedOrder(OrderRequest orderRequest, Long memberId) {
        Long productId = orderRequest.getOrderPayment().getProductId();

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new OrderMemberNotFoundException(
                        OrderErrorCode.ORDER_MEMBER_NOT_FOUND,
                        String.format("proceedOrder : memberId가 $d인 member가 없습니다. ", memberId)
                )
        );

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new OrderProductNotFoundException(
                        OrderErrorCode.ORDER_PRODUCT_NOT_FOUND,
                        String.format("proceedOrder : productId가 $d인 product가 없습니다. ", productId)
                )
        );

        //paymentService.payment();

        return OrderResponse.fromEntity(
                orderRepository.save(orderRequest.toEntity(member, product))
        );
    }

    public List<OrderItem.Response> getOrderItems(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new OrderMemberNotFoundException(
                        OrderErrorCode.ORDER_MEMBER_NOT_FOUND,
                        String.format("getOrderItems : memberId가 $d인 member가 없습니다. ", memberId)
                )
        );
        return orderRepository.findByMember(member)
                .stream()
                .map(OrderItem.Response::fromEntity)
                .collect(Collectors.toList());
    }

    public OrderDetail.Response getOrderDetail(Long orderId, Long memberId) {
        Order order = orderRepository.findById(orderId)
                .filter(o -> o.getMember().getId().equals(memberId))
                .orElseThrow(
                        () -> new OrderNotFoundException(
                                OrderErrorCode.ORDER_NOT_FOUND,
                                String.format("getOrderDetail : orderId가 $d인 order가 없습니다. ", orderId)
                        )
                );

        return OrderDetail.Response.fromEntity(order);

    }
}
