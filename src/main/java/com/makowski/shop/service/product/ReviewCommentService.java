package com.makowski.shop.service.product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.ProductReview;
import com.makowski.shop.entity.product.ReviewComment;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.product.ProductReviewRepository;
import com.makowski.shop.repository.product.ReviewCommentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReviewCommentService {
    
    private ReviewCommentRepository reviewCommentRepository;
    private ProductReviewRepository productReviewRepository;

    public ReviewComment createReviewComment(Long productReviewId, ReviewComment reviewComment){
        ProductReview productReview = productReviewRepository.findById(productReviewId)
            .orElseThrow(() -> new EntityNotFoundException(productReviewId, ProductReview.class));
        reviewComment.setProductReview(productReview);
        reviewComment.setDate(LocalDateTime.now());
        return reviewCommentRepository.save(reviewComment);
    }

    public ReviewComment getReviewComment(Long id){
        return reviewCommentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ReviewComment.class));
    }

    public List<ReviewComment> findByProductReviewId(Long productReviewId){
        return reviewCommentRepository.findByProductReviewId(productReviewId);
    }

    public List<ReviewComment> getAllReviewComment(){
        return reviewCommentRepository.findAll();
    }

    public void deleteReviewComment(Long id){
        reviewCommentRepository.deleteById(id);
    }

    public ReviewComment updateReviewComment(Long id, ReviewComment reviewComment){
        ReviewComment updateReviewComment = reviewCommentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ReviewComment.class));
        updateReviewComment.setContent(reviewComment.getContent());
        updateReviewComment.setDate(LocalDateTime.now());
        return reviewCommentRepository.save(updateReviewComment);
    }
}
