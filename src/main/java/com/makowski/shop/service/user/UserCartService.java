package com.makowski.shop.service.user;


import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserCart;

public interface UserCartService {
    UserCart createUserCart(User user);
    UserCart getCartByUserId(Long userId);
    UserCart addProductToUserCart(Long userId, Long productId);
    void deleteProductFromUserCart(Long userId, Long productId);
    void deleteAllProductFromUserCart(Long userId);
}
