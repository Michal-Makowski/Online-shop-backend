package com.makowski.shop.repository.user;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.UserFavoriteProducts;

public interface UserFavoriteProductsRepository extends ListCrudRepository<UserFavoriteProducts, Long>{
    Optional<UserFavoriteProducts> findByUserId(Long userId);
}
