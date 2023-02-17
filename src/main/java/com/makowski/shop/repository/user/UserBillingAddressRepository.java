package com.makowski.shop.repository.user;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.UserBillingAddress;

public interface UserBillingAddressRepository extends ListCrudRepository<UserBillingAddress, Long>{
    List<UserBillingAddress> findByUserId(Long userId);
}
