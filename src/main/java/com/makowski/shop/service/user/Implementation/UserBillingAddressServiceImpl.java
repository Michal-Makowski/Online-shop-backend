package com.makowski.shop.service.user.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserBillingAddress;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.user.UserBillingAddressRepository;
import com.makowski.shop.repository.user.UserRepository;
import com.makowski.shop.service.user.UserBillingAddressService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserBillingAddressServiceImpl implements UserBillingAddressService{
    
    private UserBillingAddressRepository userBillingAddressRepository;
    private UserRepository userRepository;

    @Override
    public UserBillingAddress createUserBillingAddress(UserBillingAddress userBillingAddress, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        userBillingAddress.setUser(user);
        return userBillingAddressRepository.save(userBillingAddress);
    }

    @Override
    public UserBillingAddress getUserBillingAddressById(Long id) {
        return userBillingAddressRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, UserBillingAddress.class));
    }

    @Override
    public List<UserBillingAddress> getAllUserBillingAddressByUserId(Long userId) {
        return userBillingAddressRepository.findByUserId(userId);
    }

    @Override
    public void deleteUserBillingArdress(Long id) {
        userBillingAddressRepository.deleteById(id);
        
    }

    @Override
    public UserBillingAddress updatUserBillingAddress(Long id, UserBillingAddress userBillingAddress) {
        UserBillingAddress updateUserBillingAddress = userBillingAddressRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, UserBillingAddress.class));
        updateUserBillingAddress.setFirstName(userBillingAddress.getFirstName());
        updateUserBillingAddress.setLastName(userBillingAddress.getLastName());
        updateUserBillingAddress.setCity(userBillingAddress.getCity());
        updateUserBillingAddress.setHouseNumber(userBillingAddress.getHouseNumber());
        updateUserBillingAddress.setStreet(userBillingAddress.getStreet());
        updateUserBillingAddress.setPostcode(userBillingAddress.getPostcode());
        return userBillingAddressRepository.save(updateUserBillingAddress);    
    }
    
    
}
