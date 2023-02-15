package com.makowski.shop.repository.product;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.product.ReviewComment;

public interface ReviewCommentRepository extends ListCrudRepository<ReviewComment, Long>{
    List<ReviewComment> findByProductReviewId(Long productReviewId);
}
