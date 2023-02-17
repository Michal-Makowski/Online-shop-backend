package com.makowski.shop.repository.user;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.UserFavoriteProducts;

public interface UserFavoriteProductsRepository extends ListCrudRepository<UserFavoriteProducts, Long>{
    UserFavoriteProducts findByUserId(Long userId);
}
