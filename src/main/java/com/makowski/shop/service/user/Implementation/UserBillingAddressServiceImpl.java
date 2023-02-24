package com.makowski.shop.service.user.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserBillingAddress;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.user.UserBillingAddressRepository;
import com.makowski.shop.security.MySecurityContextHolder;
import com.makowski.shop.service.user.UserBillingAddressService;
import com.makowski.shop.service.user.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserBillingAddressServiceImpl implements UserBillingAddressService {

    private UserBillingAddressRepository userBillingAddressRepository;
    private UserService userService;
    private MySecurityContextHolder mySecurityContextHolder;

    @Override
    public UserBillingAddress createUserBillingAddress(UserBillingAddress userBillingAddress, Long userId) {
        mySecurityContextHolder.userIsValid(userId);
        User user = userService.getUserById(userId);
        mySecurityContextHolder.userIsValid(user.getUsername());
        userBillingAddress.setUser(user);
        return userBillingAddressRepository.save(userBillingAddress);
    }

    @Override
    public UserBillingAddress getUserBillingAddressById(Long id) {
        mySecurityContextHolder.userIsValid(id);
        return userBillingAddressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, UserBillingAddress.class));
    }

    @Override
    public List<UserBillingAddress> getAllUserBillingAddressByUserId(Long userId) {
        mySecurityContextHolder.userIsValid(userId);
        return userBillingAddressRepository.findByUserId(userId);
    }

    @Override
    public void deleteUserBillingArdress(Long id) {
        mySecurityContextHolder.userIsValid(id);
        userBillingAddressRepository.deleteById(id);

    }

    @Override
    public UserBillingAddress updatUserBillingAddress(Long id, UserBillingAddress userBillingAddress) {
        mySecurityContextHolder.userIsValid(id);
        UserBillingAddress updateUserBillingAddress = getUserBillingAddressById(id);
        updateUserBillingAddress.setFirstName(userBillingAddress.getFirstName());
        updateUserBillingAddress.setLastName(userBillingAddress.getLastName());
        updateUserBillingAddress.setCity(userBillingAddress.getCity());
        updateUserBillingAddress.setHouseNumber(userBillingAddress.getHouseNumber());
        updateUserBillingAddress.setStreet(userBillingAddress.getStreet());
        updateUserBillingAddress.setPostcode(userBillingAddress.getPostcode());
        return userBillingAddressRepository.save(updateUserBillingAddress);
    }

}
