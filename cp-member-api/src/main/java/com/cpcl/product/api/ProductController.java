package com.cpcl.product.api;

import com.cpcl.product.api.search.ProductSearchParams;
import com.cpcl.product.application.ProductService;
import com.cpcl.product.dto.ProductDetail;
import com.cpcl.product.dto.ProductItem;
import com.cpcl.product.api.search.ProductSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetail.Response getProductDetail(@PathVariable Long id) {
        return productService.getProductDetail(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductItem.Response> searchProductItems(@ProductSearchParams ProductSearchCondition productSearchCondition) {
        return productService.searchProductItems(productSearchCondition);
    }

}
