package com.makowski.shop.service.product.Implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.ProductReview;
import com.makowski.shop.entity.product.ReviewComment;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.product.ProductReviewRepository;
import com.makowski.shop.repository.product.ReviewCommentRepository;
import com.makowski.shop.service.product.ReviewCommentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReviewCommentServiceImpl implements ReviewCommentService{
    
    private ReviewCommentRepository reviewCommentRepository;
    private ProductReviewRepository productReviewRepository;

    @Override
    public ReviewComment createReviewComment(Long productReviewId, ReviewComment reviewComment){
        ProductReview productReview = productReviewRepository.findById(productReviewId)
            .orElseThrow(() -> new EntityNotFoundException(productReviewId, ProductReview.class));
        reviewComment.setProductReview(productReview);
        reviewComment.setDate(LocalDateTime.now());
        return reviewCommentRepository.save(reviewComment);
    }

    @Override
    public ReviewComment getReviewComment(Long id){
        return reviewCommentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ReviewComment.class));
    }

    @Override
    public List<ReviewComment> findByProductReviewId(Long productReviewId){
        return reviewCommentRepository.findByProductReviewId(productReviewId);
    }

    @Override
    public List<ReviewComment> getAllReviewComment(){
        return reviewCommentRepository.findAll();
    }

    @Override
    public void deleteReviewComment(Long id){
        reviewCommentRepository.deleteById(id);
    }

    @Override
    public ReviewComment updateReviewComment(Long id, ReviewComment reviewComment){
        ReviewComment updateReviewComment = reviewCommentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, ReviewComment.class));
        updateReviewComment.setContent(reviewComment.getContent());
        updateReviewComment.setDate(LocalDateTime.now());
        return reviewCommentRepository.save(updateReviewComment);
    }
}
