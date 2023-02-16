package com.makowski.shop.service.product;

import java.util.List;

import com.makowski.shop.entity.product.ProductReview;

public interface ProductReviewService {
    ProductReview createProductReview(Long productId , ProductReview productReview);
    ProductReview getProductReview(Long id);
    List<ProductReview> findByProductId(Long productId);
    List<ProductReview> getAllProductReview();
    void deleteProductReview(Long id);
    ProductReview updateProductReview(Long id, ProductReview productReview);
}
