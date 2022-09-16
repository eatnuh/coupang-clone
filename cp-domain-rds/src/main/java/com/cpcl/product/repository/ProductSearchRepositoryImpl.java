package com.cpcl.product.repository;

import com.cpcl.product.Product;
import com.cpcl.product.type.ProductCategory;
import com.cpcl.product.type.ProductStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.cpcl.product.QProduct.product;

public class ProductSearchRepositoryImpl extends QuerydslRepositorySupport implements ProductSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ProductSearchRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Product.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Product> searchProduct(ProductCategory productCategory, Pageable pageable) {
        JPAQuery<Product> jpaQuery = jpaQueryFactory.selectFrom(product)
                .where(
                        productCategoryInclude(productCategory),
                        productIsSale()
                );

        List<Product> result = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();

        return result;
    }

    private BooleanExpression productCategoryInclude(ProductCategory productCategory) {
        return productCategory == ProductCategory.ROOT ?
                null : product.productCategory.stringValue().startsWith(productCategory.getPrefix());
    }

    private BooleanExpression productIsSale() {
        return product.productStatus.eq(ProductStatus.SALE);
    }
}
