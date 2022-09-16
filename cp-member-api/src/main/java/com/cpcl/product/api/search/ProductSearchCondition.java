package com.cpcl.product.api.search;

import com.cpcl.product.type.ProductCategory;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.Optional;

public class ProductSearchCondition {

    private static final int PAGE_SIZE = 10;
    private static final ProductCategory DEFAULT_PRODUCT_CATEGORY = ProductCategory.ROOT;
    private static final int DEFAULT_PAGE = 0;
    private static final ProductSort DEFAULT_SORT_BY = ProductSort.NEWEST;

    private ProductCategory productCategory;
    private int page;
    private ProductSort productSort;

    public ProductSearchCondition(Map<String, String[]> parameterMap) {
        this.productCategory = getValue(parameterMap, "productCategory")
                .map(ProductCategory::valueOf)
                .orElse(DEFAULT_PRODUCT_CATEGORY);
        this.page = getValue(parameterMap, "page")
                .map(Integer::parseInt)
                .filter(page -> page > 0)
                .orElse(DEFAULT_PAGE);
        this.productSort = getValue(parameterMap, "sortBy")
                .map(ProductSort::valueOf)
                .orElse(DEFAULT_SORT_BY);
    }

    private Optional<String> getValue(Map<String, String[]> parameterMap, String key) {
        return parameterMap.containsKey(key) ? Optional.of(parameterMap.get(key)[0]) : Optional.empty();
    }

    public ProductCategory getProductCategory() {
        return this.productCategory;
    }

    public PageRequest getPageRequest() {
        return PageRequest.of(this.page, PAGE_SIZE, this.productSort.getSort());
    }

    @Getter
    private enum ProductSort {
        NEWEST(Sort.by("id").descending()),
        LOW_PRICE(Sort.by("price").ascending()),
        HIGH_PRICE(Sort.by("price").descending());

        private final Sort sort;

        ProductSort(Sort sort) {
            this.sort = sort;
        }
    }
}
