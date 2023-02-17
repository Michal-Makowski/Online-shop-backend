package com.makowski.shop.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makowski.shop.entity.user.UserFavoriteProducts;
import com.makowski.shop.service.user.UserFavoriteProductsService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/userFavoriteProducts")
public class UserFavoriteProductsController {
    
    private UserFavoriteProductsService userFavoriteProductsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserFavoriteProducts> getFavoriteProductsByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(userFavoriteProductsService.getFavoriteProductsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<UserFavoriteProducts> addProductToUserFavoriteProducts(@PathVariable Long userId, @PathVariable Long productId){
        return new ResponseEntity<>(userFavoriteProductsService.addProductToUserFavoriteProducts(userId, productId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<UserFavoriteProducts> deleteProductFromUserFavoriteProducts(@PathVariable Long userId, @PathVariable Long productId){
        userFavoriteProductsService.deleteProductFromUserFavoriteProducts(userId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<UserFavoriteProducts> deleteAllProductFromUserFavoriteProducts(@PathVariable Long userId){
        userFavoriteProductsService.deleteAllProductFromUserFavoriteProducts(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
