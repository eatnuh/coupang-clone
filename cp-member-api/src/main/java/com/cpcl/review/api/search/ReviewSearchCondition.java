package com.cpcl.review.api.search;

import com.cpcl.product.type.ProductCategory;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.Optional;

public class ReviewSearchCondition {

    private static final int PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE = 0;
    private static final ReviewSort DEFAULT_SORT_BY = ReviewSort.NEWEST;

    private int page;
    private ReviewSort reviewSort;

    public ReviewSearchCondition(Map<String, String[]> parameterMap) {
        this.page = getValue(parameterMap, "page")
                .map(Integer::parseInt)
                .filter(page -> page > 0)
                .orElse(DEFAULT_PAGE);
        this.reviewSort = getValue(parameterMap, "sortBy")
                .map(ReviewSort::valueOf)
                .orElse(DEFAULT_SORT_BY);
    }

    private Optional<String> getValue(Map<String, String[]> parameterMap, String key) {
        return parameterMap.containsKey(key) ? Optional.of(parameterMap.get(key)[0]) : Optional.empty();
    }

    public PageRequest getPageRequest() {
        return PageRequest.of(this.page, PAGE_SIZE, this.reviewSort.getSort());
    }

    @Getter
    private enum ReviewSort {
        NEWEST(Sort.by("id").descending()),
        LOW_RATING(Sort.by("rating").ascending()),
        HIGH_RATING(Sort.by("rating").descending());

        private final Sort sort;

        ReviewSort(Sort sort) {
            this.sort = sort;
        }
    }
}
