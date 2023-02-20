package com.makowski.shop.repository.user;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.UserLastProducts;

public interface UserLastProductsRepository extends ListCrudRepository<UserLastProducts, Long>{
    Optional<UserLastProducts> findByUserId(Long userId);
}
