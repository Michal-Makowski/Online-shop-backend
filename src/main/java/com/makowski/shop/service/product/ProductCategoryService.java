package com.makowski.shop.service.product;

import java.util.List;

import com.makowski.shop.entity.product.ProductCategory;

public interface ProductCategoryService {
    ProductCategory createProductCategory(ProductCategory productCategory);
    ProductCategory getProductCategory(Long id);
    List<ProductCategory> getAllProductCategory();
    void deleteProductCategory(Long id);
    ProductCategory updateProductCategory(Long id, ProductCategory productCategory);
}
