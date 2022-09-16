package com.cpcl.product.type;

import com.cpcl.exception.BusinessException;
import lombok.Getter;

import java.util.*;

@Getter
public enum ProductCategory {
    ROOT(
            "C", "카테고리", null
    ),
    FASHION(
            "C1", "패션의류잡화", ROOT
    ),
    FASHION_MEN(
            "C11", "남성패션", FASHION
    ),
    FASHION_MEN_CLOTHING(
            "C111", "남성의류", FASHION_MEN
    ),
    FASHION_MEN_SHOES(
            "C112", "남성화", FASHION_MEN
    ),
    FASHION_MEN_WATCH(
            "C113", "남성시계", FASHION_MEN
    ),
    FASHION_WOMEN(
            "C12", "여성패션", FASHION
    ),
    FASHION_WOMEN_CLOTHING(
            "C121", "여성의류", FASHION_WOMEN
    ),
    FASHION_WOMEN_SHOES(
            "C122", "여성화", FASHION_WOMEN
    ),
    FASHION_WOMEN_ACCESSORIES(
            "C123", "여성잡화", FASHION_WOMEN
    ),
    FOOD(
            "C2", "식품", ROOT
    ),
    FOOD_INSTANT(
            "C21", "가공/즉석식품", FOOD
    ),
    FOOD_BEVERAGE(
            "C22", "생수/음료", FOOD
    ),
    FOOD_FRESH(
            "C23", "신선식품", FOOD
    ),
    DIGITAL(
            "C3", "가전/디지털", ROOT
    ),
    DIGITAL_VIDEO(
            "C31", "TV/영상가전", DIGITAL
    ),
    DIGITAL_VIDEO_TV(
            "C311", "TV", DIGITAL_VIDEO
    ),
    DIGITAL_VIDEO_PROJECTOR(
            "C312", "프로젝터/스크린", DIGITAL_VIDEO
    ),
    DIGITAL_COMPUTER(
            "C32", "컴퓨터/게임/SW", DIGITAL
    );

    private final String prefix;
    private final String description;
    private final Optional<ProductCategory> parent;
    private final List<ProductCategory> children = new ArrayList<>();

    ProductCategory(String prefix, String description, ProductCategory parent) {
        this.prefix = prefix;
        this.description = description;
        this.parent = Optional.ofNullable(parent);
        this.parent.ifPresent(
                productCategory -> productCategory.children.add(this)
        );
    }
    public List<ProductCategory> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Boolean isLastProductCategory() {
        return children.isEmpty();
    }

    public static ProductCategory ofPrefix(String prefix) {
        return Arrays.stream(ProductCategory.values())
                .filter(productCategory -> productCategory.getPrefix().equals(prefix))
                .findAny()
                .get();
    }
}
