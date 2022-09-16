package com.cpcl.order;

import com.cpcl.common.Address;
import com.cpcl.member.Member;
import com.cpcl.order.Order;
import com.cpcl.order.payment.Payment;
import com.cpcl.order.shipping.Shipping;
import com.cpcl.order.type.OrderStatus;
import com.cpcl.product.Product;
import com.cpcl.product.type.ProductStatus;
import com.cpcl.order.payment.type.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("주문 도메인 테스트")
class OrderTest {

    @Test
    @DisplayName("주문 생성 테스트")
    void createOrder() {
        //given
        //when
        Shipping shipping = Shipping.of(
                "mike",
                "010-0000-0000",
                Address.of(
                        "01-123",
                        "서울특별시",
                        "강남구")

        );

        Payment payment = Payment.of(7000, PaymentType.CREDIT_CARD);
        Member member = Member.builder()
                .email("abc@abc.com")
                .password("1234")
                .build();
        Product product = Product.builder()
                .name("A product")
                .productStatus(ProductStatus.SALE)
                .build();

        Order order = Order.builder()
                .shipping(shipping)
                .payment(payment)
                .member(member)
                .product(product)
                .build();
        //then
        assertThat(order.getShipping()).isEqualTo(shipping);
        assertThat(order.getPayment()).isEqualTo(payment);
        assertThat(order.getMember()).isEqualTo(member);
        assertThat(order.getProduct()).isEqualTo(product);

    }

    @Test
    @DisplayName("주문 완료 여부 확인 테스트")
    void isNotCompleted() {
        //given
        //when
        Order completedPaymentOrder = Order.builder().orderStatus(OrderStatus.COMPLETE_PAYMENT).build();
        Order productPreparedOrder = Order.builder().orderStatus(OrderStatus.PRODUCT_PREPARED).build();
        Order shippingStartOrder = Order.builder().orderStatus(OrderStatus.SHIPPING_START).build();
        Order shippingOrder = Order.builder().orderStatus(OrderStatus.SHIPPING).build();
        Order shippingCompletedOrder = Order.builder().orderStatus(OrderStatus.SHIPPING_COMPLETED).build();

        //then
        assertThat(completedPaymentOrder.isNotCompleted()).isTrue();
        assertThat(productPreparedOrder.isNotCompleted()).isTrue();
        assertThat(shippingStartOrder.isNotCompleted()).isTrue();
        assertThat(shippingOrder.isNotCompleted()).isTrue();
        assertThat(shippingCompletedOrder.isNotCompleted()).isFalse();

    }
}