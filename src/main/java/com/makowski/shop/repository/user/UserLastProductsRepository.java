package com.makowski.shop.repository.user;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.UserLastProducts;

public interface UserLastProductsRepository extends ListCrudRepository<UserLastProducts, Long>{
    UserLastProducts findByUserId(Long userId);
}
