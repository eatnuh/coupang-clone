package com.cpcl.order.repository;

import com.cpcl.RepositoryTest;
import com.cpcl.common.Address;
import com.cpcl.member.Member;
import com.cpcl.member.repository.MemberRepository;
import com.cpcl.order.Order;
import com.cpcl.order.payment.Payment;
import com.cpcl.order.payment.type.PaymentType;
import com.cpcl.order.shipping.Shipping;
import com.cpcl.product.Product;
import com.cpcl.product.repository.ProductRepository;
import com.cpcl.product.type.ProductCategory;
import com.cpcl.product.type.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("OrderRepository 테스트")
class OrderRepositoryTest extends RepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    private Order givenOrder;

    @BeforeEach
    void setUp() {
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
                .productCategory(ProductCategory.FASHION_MEN_CLOTHING)
                .build();

        givenOrder = Order.builder()
                .shipping(shipping)
                .payment(payment)
                .member(member)
                .product(product)
                .build();
    }

    @DisplayName("Order 저장")
    @Test
    void save() {
        //given
        //when
        Order savedOrder = orderRepository.save(givenOrder);

        //then
        assertThat(savedOrder.getId()).isNotNull();
    }

    @DisplayName("Order id 조회")
    @Test
    void findById() {
        //given
        Order savedOrder = orderRepository.save(givenOrder);
        //when
        Optional<Order> findMember = orderRepository.findById(savedOrder.getId());
        //then
        assertThat(findMember).containsSame(savedOrder);
    }

    @DisplayName("Order Member 조회")
    @Test
    void findByMember() {
        //given
        Member savedMember = memberRepository.save(givenOrder.getMember());
        Product savedProduct = productRepository.save(givenOrder.getProduct());
        Order savedOrder = orderRepository.save(givenOrder);
        //when
        List<Order> findOrderList = orderRepository.findByMember(savedMember);
        //then
        assertThat(findOrderList).isNotEmpty();
        assertThat(findOrderList.get(0)).isEqualTo(givenOrder);
    }
}