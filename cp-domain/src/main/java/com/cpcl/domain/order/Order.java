package com.cpcl.domain.order;


import com.cpcl.domain.common.BaseTimeEntity;
import com.cpcl.domain.member.Member;
import com.cpcl.domain.product.Product;
import com.cpcl.domain.product.ProductCategory;
import com.cpcl.domain.product.ProductStatus;
import com.cpcl.domain.seller.Seller;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order")
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public Order(Shipping shipping, Payment payment, Member member, Product product) {
        this.shipping = shipping;
        this.payment = payment;
        this.member = member;
        this.product = product;
    }
}
