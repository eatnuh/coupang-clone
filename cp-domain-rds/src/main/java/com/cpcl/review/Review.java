package com.cpcl.review;

import com.cpcl.common.BaseTimeEntity;
import com.cpcl.member.Member;
import com.cpcl.order.Order;
import com.cpcl.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public Review(Integer rating, String content, Member member, Product product, Order order) {
        this.rating = rating;
        this.content = content;
        this.member = member;
        this.product = product;
        this.order = order;
    }
}
