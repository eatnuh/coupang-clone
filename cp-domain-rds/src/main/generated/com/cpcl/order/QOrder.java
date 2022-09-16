package com.cpcl.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -159644413L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final com.cpcl.common.QBaseTimeEntity _super = new com.cpcl.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.cpcl.member.QMember member;

    public final EnumPath<com.cpcl.order.type.OrderStatus> orderStatus = createEnum("orderStatus", com.cpcl.order.type.OrderStatus.class);

    public final com.cpcl.order.payment.QPayment payment;

    public final com.cpcl.product.QProduct product;

    public final com.cpcl.order.purchase.QPurchase purchase;

    public final com.cpcl.order.shipping.QShipping shipping;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.cpcl.member.QMember(forProperty("member")) : null;
        this.payment = inits.isInitialized("payment") ? new com.cpcl.order.payment.QPayment(forProperty("payment")) : null;
        this.product = inits.isInitialized("product") ? new com.cpcl.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.purchase = inits.isInitialized("purchase") ? new com.cpcl.order.purchase.QPurchase(forProperty("purchase")) : null;
        this.shipping = inits.isInitialized("shipping") ? new com.cpcl.order.shipping.QShipping(forProperty("shipping"), inits.get("shipping")) : null;
    }

}

