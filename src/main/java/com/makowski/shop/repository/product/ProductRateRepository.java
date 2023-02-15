package com.makowski.shop.repository.product;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.product.ProductRate;

public interface ProductRateRepository extends ListCrudRepository<ProductRate, Long>{
    
}
