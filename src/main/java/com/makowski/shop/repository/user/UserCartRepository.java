package com.makowski.shop.repository.user;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.UserCart;

public interface UserCartRepository extends ListCrudRepository<UserCart, Long>{
    UserCart findByUserId(Long userId);
}
