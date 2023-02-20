package com.makowski.shop.repository.user;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.UserCart;

public interface UserCartRepository extends ListCrudRepository<UserCart, Long>{
    Optional<UserCart> findByUserId(Long userId);
}
