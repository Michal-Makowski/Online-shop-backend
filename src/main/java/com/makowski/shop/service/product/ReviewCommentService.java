package com.makowski.shop.service.product;

import java.util.List;

import com.makowski.shop.entity.product.ReviewComment;

public interface ReviewCommentService {
    ReviewComment createReviewComment(Long productReviewId, ReviewComment reviewComment);
    ReviewComment getReviewComment(Long id);
    List<ReviewComment> findByProductReviewId(Long productReviewId);
    List<ReviewComment> getAllReviewComment();
    void deleteReviewComment(Long id);
    ReviewComment updateReviewComment(Long id, ReviewComment reviewComment);
}
