package com.makowski.shop.security;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.makowski.shop.entity.product.ProductReview;
import com.makowski.shop.entity.product.ReviewComment;
import com.makowski.shop.entity.user.Role;
import com.makowski.shop.entity.user.User;
import com.makowski.shop.exception.NotAuthorizeException;
import com.makowski.shop.service.product.ProductReviewService;
import com.makowski.shop.service.product.ReviewCommentService;
import com.makowski.shop.service.user.UserService;

import lombok.AllArgsConstructor;
//to avoid Circular Dependencies onConstructor = @__(@Lazy)
@AllArgsConstructor(onConstructor = @__(@Lazy))
@Component
public class MySecurityContextHolder {
    @Lazy
    private UserService userService;
    private ProductReviewService productReviewService;
    private ReviewCommentService reviewCommentService;

    public boolean userIsValid(String username) {
        User authUser = authUser();
        User user = userService.getUserByUsername(username);
        if (user.equals(authUser) || authUser.getRole().equals(Role.ADMIN)) {
            return true;
        } else {
            throw new NotAuthorizeException(authUser.getUsername());
        }
    }

    public boolean userIsValid(Long id) {
        User authUser = authUser();
        User user = userService.getUserById(id);
        if (user.equals(authUser) || authUser.getRole().equals(Role.ADMIN)) {
            return true;
        } else {
            throw new NotAuthorizeException(authUser.getUsername());
        }
    }

       public boolean ownReview(Long id){
        User authUser = authUser();
        ProductReview productReview = productReviewService.getProductReview(id);
        if(productReview.getUser().equals(authUser) || authUser.getRole().equals(Role.ADMIN)){
            return true;
        } else {
            throw new NotAuthorizeException(authUser.getUsername(), ProductReview.class);
        }
    }

    public boolean ownComment(Long id){
        User authUser = authUser();
        ReviewComment reviewComment = reviewCommentService.getReviewComment(id);
        if(reviewComment.getUser().equals(authUser) || authUser.getRole().equals(Role.ADMIN)){
            return true;
        } else {
            throw new NotAuthorizeException(authUser.getUsername(), ReviewComment.class);
        }
    }

    public User authUser(){
        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User authUser = userService.getUserByUsername(authUsername);
        return authUser;
    }
}
