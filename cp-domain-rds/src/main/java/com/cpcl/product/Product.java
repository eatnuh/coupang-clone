package com.cpcl.product;


import com.cpcl.common.BaseTimeEntity;
import com.cpcl.product.type.ProductCategoryToPrefixConverter;
import com.cpcl.product.type.ProductStatus;
import com.cpcl.seller.Seller;
import com.cpcl.product.type.ProductCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.naming.Name;
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
    @Column(name = "product_status")
    private ProductStatus productStatus;

    @Convert(converter = ProductCategoryToPrefixConverter.class)
    @Column(name = "product_category")
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
