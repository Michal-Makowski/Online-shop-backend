package com.makowski.shop.web.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makowski.shop.entity.product.ReviewComment;
import com.makowski.shop.service.product.ReviewCommentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/ReviewComment")
public class ReviewCommentController {
    
    private ReviewCommentService reviewCommentService;

    @PostMapping("/productReview/{productReviewId}")
    public ResponseEntity<ReviewComment> createReviewComment(@PathVariable Long productReviewId, @Valid @RequestBody ReviewComment reviewComment){
        return new ResponseEntity<>(reviewCommentService.createReviewComment(productReviewId ,reviewComment), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewComment> getReviewComment(@PathVariable Long id){
        return new ResponseEntity<>(reviewCommentService.getReviewComment(id), HttpStatus.OK);
    }

    @GetMapping("/productReview/{productReviewId}")
    public ResponseEntity<List<ReviewComment>> findByProductReviewId(@PathVariable Long productReviewId){
        return new ResponseEntity<>(reviewCommentService.findByProductReviewId(productReviewId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewComment>> getAllReviewComments(){
        return new ResponseEntity<>(reviewCommentService.getAllReviewComment(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewComment> deleteReviewComment(@PathVariable Long id){
        reviewCommentService.deleteReviewComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewComment> updateReviewComment (@PathVariable Long id, @RequestBody ReviewComment reviewComment){
        return new ResponseEntity<>(reviewCommentService.updateReviewComment(id, reviewComment) , HttpStatus.OK);
    }
}


