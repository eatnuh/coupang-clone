package com.cpcl.order.purchase;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {
    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "shipping_fee")
    private Integer shippingFee;

    @Column(name = "discount_price")
    private Integer discountPrice;

    @Column(name = "final_Price")
    private Integer finalPrice;

    @Builder
    public Purchase(String productName, Integer price, Integer quantity, Integer totalPrice, Integer shippingFee, Integer discountPrice, Integer finalPrice) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.discountPrice = discountPrice;
        this.finalPrice = finalPrice;
    }
}
