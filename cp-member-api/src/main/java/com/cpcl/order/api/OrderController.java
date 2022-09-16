package com.cpcl.order.api;

import com.cpcl.member.Member;
import com.cpcl.order.application.OrderService;
import com.cpcl.order.dto.OrderDetail;
import com.cpcl.order.dto.OrderItem;
import com.cpcl.order.dto.OrderRequest;
import com.cpcl.order.dto.OrderResponse;
import com.cpcl.order.validation.group.ValidationSequence;
import com.cpcl.security.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse proceedOrder(@RequestBody @Validated(ValidationSequence.class) OrderRequest orderRequest,
                                      @AuthMember Member member
    ) {
        return orderService.proceedOrder(orderRequest, member.getId());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItem.Response> getOrderItems(@AuthMember Member member) {
        return orderService.getOrderItems(member.getId());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetail.Response getOrderDetail(@PathVariable Long orderId,
                                               @AuthMember Member member) {
        return orderService.getOrderDetail(orderId, member.getId());
    }
}
