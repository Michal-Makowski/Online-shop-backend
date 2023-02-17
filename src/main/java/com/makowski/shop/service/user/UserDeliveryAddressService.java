package com.makowski.shop.service.user;

import java.util.List;

import com.makowski.shop.entity.user.UserDeliveryAddress;

public interface UserDeliveryAddressService {
    UserDeliveryAddress createUserDeliveryAddress(UserDeliveryAddress userDeliveryAddress, Long userId);
    UserDeliveryAddress getUserDeliveryAddressById(Long id);
    List<UserDeliveryAddress> getAllUserDeliveryAddressByUserId(Long userId);
    void deleteUserDeliveryArdress(Long id);
    UserDeliveryAddress updatUserDeliveryAddress(Long id, UserDeliveryAddress userDeliveryAddress);
}
