package com.cpcl.product.type;

import com.cpcl.product.type.ProductCategory;

import javax.persistence.AttributeConverter;

public class ProductCategoryToPrefixConverter implements AttributeConverter<ProductCategory, String> {
    @Override
    public String convertToDatabaseColumn(ProductCategory productCategory) {
        return productCategory.getPrefix();
    }

    @Override
    public ProductCategory convertToEntityAttribute(String prefix) {
        return ProductCategory.ofPrefix(prefix);
    }
}
