package com.makowski.shop.repository.user;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.UserDeliveryAddress;

public interface UserDeliveryAddressRepository extends ListCrudRepository<UserDeliveryAddress, Long>{
    List<UserDeliveryAddress> findByUserId(Long userId);
}
