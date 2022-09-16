package com.cpcl.product.repository;

import com.cpcl.product.Product;
import com.cpcl.product.type.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductSearchRepository {

    List<Product> searchProduct(ProductCategory productCategory, Pageable pageable);
}
