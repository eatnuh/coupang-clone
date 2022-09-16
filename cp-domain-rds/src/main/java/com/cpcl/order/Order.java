package com.cpcl.order;


import com.cpcl.common.BaseTimeEntity;
import com.cpcl.member.Member;
import com.cpcl.order.payment.Payment;
import com.cpcl.order.purchase.Purchase;
import com.cpcl.order.shipping.Shipping;
import com.cpcl.order.type.OrderStatus;
import com.cpcl.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Embedded
    private Shipping shipping;

    @Embedded
    private Payment payment;

    @Embedded
    private Purchase purchase;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public Order(
            Shipping shipping, Payment payment,
            Purchase purchase, OrderStatus orderStatus,
            Member member, Product product) {
        this.shipping = shipping;
        this.payment = payment;
        this.purchase = purchase;
        this.orderStatus = orderStatus;
        this.member = member;
        this.product = product;
    }

    public Boolean isNotCompleted() {
        return orderStatus != OrderStatus.SHIPPING_COMPLETED;
    }
}
