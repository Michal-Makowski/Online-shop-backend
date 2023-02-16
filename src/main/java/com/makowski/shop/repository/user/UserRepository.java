package com.makowski.shop.repository.user;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.User;

public interface UserRepository extends ListCrudRepository<User , Long>{
    Optional<User> findByUsername (String username);
}
