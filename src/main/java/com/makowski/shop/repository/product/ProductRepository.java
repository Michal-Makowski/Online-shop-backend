package com.makowski.shop.repository.product;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.product.Product;

public interface ProductRepository extends ListCrudRepository<Product, Long>{
    List<Product> findByProductCategoryId(Long productCategoryId);
    List<Product> findByProductCategoryIdAndProductRatesId(Long productCategoryId, Long productRateId);
    List<Product> findByNameContainingIgnoreCase(String name);
}
