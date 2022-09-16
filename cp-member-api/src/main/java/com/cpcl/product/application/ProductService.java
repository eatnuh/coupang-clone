package com.cpcl.product.application;

import com.cpcl.product.api.search.ProductSearchCondition;
import com.cpcl.product.dto.ProductDetail;
import com.cpcl.product.dto.ProductItem;
import com.cpcl.product.exception.ProductErrorCode;
import com.cpcl.product.exception.ProductNotFoundException;
import com.cpcl.product.repository.ProductRepository;
import com.cpcl.product.type.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDetail.Response getProductDetail(Long productId) {
        return ProductDetail.Response.fromEntity(
                productRepository.findById(productId)
                        .orElseThrow(
                                () -> new ProductNotFoundException(
                                        ProductErrorCode.PRODUCT_NOT_FOUND,
                                        String.format("getProductDetail : productId가 %d인 상품이 없습니다.", productId)
                                )
                        )
        );
    }

    @Transactional(readOnly = true)
    public List<ProductItem.Response> searchProductItems(ProductSearchCondition productSearchCondition) {
        ProductCategory productCategory = productSearchCondition.getProductCategory();
        Pageable pageable = productSearchCondition.getPageRequest();

        return productRepository.searchProduct(productCategory, pageable).stream()
                .map(ProductItem.Response::fromEntity)
                .collect(Collectors.toList());
    }
}
