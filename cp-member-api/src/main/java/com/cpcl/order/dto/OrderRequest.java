package com.cpcl.order.dto;

import com.cpcl.common.Address;
import com.cpcl.member.Member;
import com.cpcl.order.Order;
import com.cpcl.order.payment.Payment;
import com.cpcl.order.purchase.Purchase;
import com.cpcl.order.shipping.Shipping;
import com.cpcl.order.validation.group.ValidationSequence.*;
import com.cpcl.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderRequest {

    @NotNull(groups = ExistCheck.class,
            message = "배송정보가 없습니다.")
    @Valid
    private OrderShipping orderShipping;

    @NotNull(groups = ExistCheck.class,
            message = "결제수단 정보가 없습니다.")
    @Valid
    private OrderPaymentMethod orderPaymentMethod;

    @NotNull(groups = ExistCheck.class,
            message = "결제정보가 없습니다.")
    @Valid
    private OrderPayment orderPayment;

    public Order toEntity(Member member, Product product) {
        return Order.builder()
                .shipping(
                        Shipping.of(
                                orderShipping.getRecipientName(),
                                orderShipping.getRecipientPhone(),
                                Address.of(
                                        orderShipping.getZipCode(),
                                        orderShipping.getAddress1(),
                                        orderShipping.getAddress2()
                                )
                        )
                )
                .payment(
                        Payment.of(orderPayment.getFinalPrice(),
                                orderPaymentMethod.getPaymentType()
                        )
                )
                .purchase(
                        Purchase.builder()
                                .productName(orderPayment.getProductName())
                                .price(orderPayment.getPrice())
                                .quantity(orderPayment.getQuantity())
                                .shippingFee(orderPayment.getShippingFee())
                                .discountPrice(orderPayment.getDiscountPrice())
                                .finalPrice(orderPayment.getFinalPrice())
                                .build()
                )
                .member(member)
                .product(product)
                .build();
    }
}
