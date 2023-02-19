package com.makowski.shop.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makowski.shop.entity.user.UserLastProducts;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.user.UserLastProductsService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/userLastProducts")
@PreAuthorize("hasAnyRole('" + SecurityConstants.CUSTOMER +"')")
public class UserLastProductsController {
    
    private UserLastProductsService userLastProductsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserLastProducts> getLastProductsByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(userLastProductsService.getLastProductsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<UserLastProducts> addProductToUserLastProducts(@PathVariable Long userId, @PathVariable Long productId){
        return new ResponseEntity<>(userLastProductsService.addProductToUserLastProducts(userId, productId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<UserLastProducts> deleteProductFromUserLastProducts(@PathVariable Long userId, @PathVariable Long productId){
        userLastProductsService.deleteProductFromUserLastProducts(userId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<UserLastProducts> deleteAllProductFromUserLastProducts(@PathVariable Long userId){
        userLastProductsService.deleteAllProductFromUserLastProducts(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
