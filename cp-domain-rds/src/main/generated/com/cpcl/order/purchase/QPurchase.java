package com.cpcl.order.purchase;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPurchase is a Querydsl query type for Purchase
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPurchase extends BeanPath<Purchase> {

    private static final long serialVersionUID = -1795441213L;

    public static final QPurchase purchase = new QPurchase("purchase");

    public final NumberPath<Integer> discountPrice = createNumber("discountPrice", Integer.class);

    public final NumberPath<Integer> finalPrice = createNumber("finalPrice", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final NumberPath<Integer> shippingFee = createNumber("shippingFee", Integer.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QPurchase(String variable) {
        super(Purchase.class, forVariable(variable));
    }

    public QPurchase(Path<? extends Purchase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPurchase(PathMetadata metadata) {
        super(Purchase.class, metadata);
    }

}

