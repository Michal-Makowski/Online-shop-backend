package com.makowski.shop.repository.product;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.product.ProductCategory;

public interface ProductCategoryRepository extends ListCrudRepository<ProductCategory, Long>{
    
}
