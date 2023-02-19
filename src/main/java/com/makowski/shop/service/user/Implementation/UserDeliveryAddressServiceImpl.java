package com.makowski.shop.service.user.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserDeliveryAddress;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.user.UserDeliveryAddressRepository;
import com.makowski.shop.service.user.UserDeliveryAddressService;
import com.makowski.shop.service.user.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDeliveryAddressServiceImpl implements UserDeliveryAddressService{
    
    private UserDeliveryAddressRepository userDeliveryAddressRepository;
    private UserService userService;

    @Override
    public UserDeliveryAddress createUserDeliveryAddress(UserDeliveryAddress userDeliveryAddress, Long userId) {
        User user = userService.getUserById(userId);
        userDeliveryAddress.setUser(user);
        return userDeliveryAddressRepository.save(userDeliveryAddress);
    }

    @Override
    public UserDeliveryAddress getUserDeliveryAddressById(Long id) {
        return userDeliveryAddressRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, UserDeliveryAddress.class));
    }

    @Override
    public List<UserDeliveryAddress> getAllUserDeliveryAddressByUserId(Long userId) {
        return userDeliveryAddressRepository.findByUserId(userId);
    }

    @Override
    public void deleteUserDeliveryArdress(Long id) {
        userDeliveryAddressRepository.deleteById(id);
        
    }

    @Override
    public UserDeliveryAddress updatUserDeliveryAddress(Long id, UserDeliveryAddress userDeliveryAddress) {
        UserDeliveryAddress updateUserDeliveryAddress = getUserDeliveryAddressById(id);
        updateUserDeliveryAddress.setFirstName(userDeliveryAddress.getFirstName());
        updateUserDeliveryAddress.setLastName(userDeliveryAddress.getLastName());
        updateUserDeliveryAddress.setCity(userDeliveryAddress.getCity());
        updateUserDeliveryAddress.setHouseNumber(userDeliveryAddress.getHouseNumber());
        updateUserDeliveryAddress.setStreet(userDeliveryAddress.getStreet());
        updateUserDeliveryAddress.setPostcode(userDeliveryAddress.getPostcode());
        return userDeliveryAddressRepository.save(updateUserDeliveryAddress);    
    }
    
    
}
