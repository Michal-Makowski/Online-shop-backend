package com.makowski.shop.repository.product;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.product.ProductReview;

public interface ProductReviewRepository extends ListCrudRepository<ProductReview, Long> {
    List<ProductReview> findByProductId(Long productId);
}
