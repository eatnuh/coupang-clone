package com.cpcl.domain.order;

import com.cpcl.domain.common.Address;
import com.cpcl.domain.member.Member;
import com.cpcl.domain.product.Product;
import com.cpcl.domain.product.ProductStatus;
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
}