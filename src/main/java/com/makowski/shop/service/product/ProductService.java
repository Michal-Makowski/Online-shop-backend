package com.makowski.shop.service.product;

import java.util.List;

import com.makowski.shop.entity.product.Product;

public interface ProductService {
    Product createProduct(Product product, Long productCategoryId);
    Product getProduct(Long id);
    List<Product> findByProductCategoryId(Long productCategoryId);
    List<Product> findByProductCategoryIdAndProductRatesId(Long productCategoryId, Long productRateId);
    List<Product> findByProductName(String productName);
    List<Product> getAllProducts();
    void deleteProduct(Long id);
    Product updateProduct(Long id, Long productCategoryId, Product product);
}
