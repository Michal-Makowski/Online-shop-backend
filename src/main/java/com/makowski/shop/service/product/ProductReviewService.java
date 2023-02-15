package com.makowski.shop.service.product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.ProductReview;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.product.ProductReviewRepository;
import com.makowski.shop.repository.product.ProductRepository;
import com.makowski.shop.entity.product.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductReviewService {
    
    private ProductReviewRepository productReviewRepository;
    private ProductRepository productRepository;

    public ProductReview createProductReview(Long productId , ProductReview productReview){
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException(productId, Product.class));
        productReview.setProduct(product);
        productReview.setDate(LocalDateTime.now());
        return productReviewRepository.save(productReview);
    }

    public ProductReview getProductReview(Long id){
        return productReviewRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ProductReview.class));
    }

    public List<ProductReview> findByProductId(Long productId){
        return productReviewRepository.findByProductId(productId);
    }

    public List<ProductReview> getAllProductReview(){
        return productReviewRepository.findAll();
    }

    public void deleteProductReview(Long id){
        productReviewRepository.deleteById(id);
    }

    public ProductReview updateProductReview(Long id, ProductReview productReview){
        ProductReview updateProductReview = productReviewRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ProductReview.class));
        updateProductReview.setTitle(productReview.getTitle());
        updateProductReview.setContent(productReview.getContent());
        updateProductReview.setDate(LocalDateTime.now());
        return productReviewRepository.save(updateProductReview);
    }
}
