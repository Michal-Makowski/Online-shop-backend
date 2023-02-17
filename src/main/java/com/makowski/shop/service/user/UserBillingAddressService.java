package com.makowski.shop.service.user;

import java.util.List;

import com.makowski.shop.entity.user.UserBillingAddress;

public interface UserBillingAddressService {
    UserBillingAddress createUserBillingAddress(UserBillingAddress userBillingAddress, Long userId);
    UserBillingAddress getUserBillingAddressById(Long id);
    List<UserBillingAddress> getAllUserBillingAddressByUserId(Long userId);
    void deleteUserBillingArdress(Long id);
    UserBillingAddress updatUserBillingAddress(Long id, UserBillingAddress userBillingAddress);
}
