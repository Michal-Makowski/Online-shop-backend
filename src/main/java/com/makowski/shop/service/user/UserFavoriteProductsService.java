package com.makowski.shop.service.user;

import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserFavoriteProducts;

public interface UserFavoriteProductsService {
    UserFavoriteProducts createUserFavoriteProducts(User user);
    UserFavoriteProducts getFavoriteProductsByUserId(Long userId);
    UserFavoriteProducts addProductToUserFavoriteProducts(Long userId, Long productId);
    void deleteProductFromUserFavoriteProducts(Long userId, Long productId);
    void deleteAllProductFromUserFavoriteProducts(Long userId);
}
