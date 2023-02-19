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

import com.makowski.shop.entity.user.UserCart;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.user.UserCartService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/userCart")
@PreAuthorize("hasAnyRole('" + SecurityConstants.CUSTOMER +"', '" + SecurityConstants.ADMIN +"')")
public class UserCartController {
    
    private UserCartService userCartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserCart> getCartByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(userCartService.getCartByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<UserCart> addProductToUserCart(@PathVariable Long userId, @PathVariable Long productId){
        return new ResponseEntity<>(userCartService.addProductToUserCart(userId, productId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<UserCart> deleteProductFromUserCart(@PathVariable Long userId, @PathVariable Long productId){
        userCartService.deleteProductFromUserCart(userId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<UserCart> deleteAllProductFromUserCart(@PathVariable Long userId){
        userCartService.deleteAllProductFromUserCart(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
