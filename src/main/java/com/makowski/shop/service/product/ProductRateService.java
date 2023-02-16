package com.makowski.shop.service.product;

import java.util.List;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.product.ProductRate;

public interface ProductRateService {
    ProductRate createProductRate(ProductRate productRate);
    ProductRate addRateToProduct(Long id, Long productId);
    ProductRate getProductRate(Long id);
    List<Product> getProductByRateId(Long id);
    List<ProductRate> getAllProductRate();
    void deleteProductRate(Long id);
    ProductRate updateProductRate(Long id, ProductRate productRate);
}
