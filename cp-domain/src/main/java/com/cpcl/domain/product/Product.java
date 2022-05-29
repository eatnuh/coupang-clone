package com.cpcl.domain.product;


import com.cpcl.domain.common.BaseTimeEntity;
import com.cpcl.domain.seller.Seller;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "price")
    private Integer price;

    @Column(name = "brand")
    private String brand;

    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;

    @Enumerated(value = EnumType.STRING)
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Builder
    public Product(String name, String content, Integer price, String brand, ProductStatus productStatus, ProductCategory productCategory, Seller seller) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.brand = brand;
        this.productStatus = productStatus;
        this.productCategory = productCategory;
        this.seller = seller;
    }
}
