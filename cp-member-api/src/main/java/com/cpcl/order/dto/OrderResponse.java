package com.cpcl.order.dto;

import com.cpcl.order.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponse {

    private Long orderId;
    public static OrderResponse fromEntity(Order order) {
        return new OrderResponse(order.getId());
    }
}
