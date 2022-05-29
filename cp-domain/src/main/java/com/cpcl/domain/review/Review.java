package com.cpcl.domain.review;

import com.cpcl.domain.common.BaseTimeEntity;
import com.cpcl.domain.member.Member;
import com.cpcl.domain.product.Product;
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

    @Builder
    public Review(Integer rating, String content, Member member, Product product) {
        this.rating = rating;
        this.content = content;
        this.member = member;
        this.product = product;
    }
}
