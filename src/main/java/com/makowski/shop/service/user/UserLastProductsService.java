package com.makowski.shop.service.user;

import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserLastProducts;

public interface UserLastProductsService {
    UserLastProducts createUserLastProducts(User user);
    UserLastProducts getLastProductsByUserId(Long userId);
    UserLastProducts addProductToUserLastProducts(Long userId, Long productId);
    void deleteProductFromUserLastProducts(Long userId, Long productId);
    void deleteAllProductFromUserLastProducts(Long userId);
}
