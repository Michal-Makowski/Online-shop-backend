package com.makowski.shop.service.user.Implementation;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.makowski.shop.entity.user.Role;
import com.makowski.shop.entity.user.User;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.user.UserRepository;
import com.makowski.shop.service.user.UserCartService;
import com.makowski.shop.service.user.UserFavoriteProductsService;
import com.makowski.shop.service.user.UserLastProductsService;
import com.makowski.shop.service.user.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserCartService userCartService;
    private UserFavoriteProductsService userFavoriteProductsService;
    private UserLastProductsService userLastProductsService;

    public User createUser (User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        userCartService.createUserCart(user);
        userFavoriteProductsService.createUserFavoriteProducts(user);
        userLastProductsService.createUserLastProducts(user);
        return user;
    }

    @Override
    public User createAdmin (User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    @Override
    public User getUserById (Long id){
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }

    @Override
    public User getUserByUsername (String username){
        return userRepository.findByUsername(username)
            .orElseThrow (() -> new EntityNotFoundException(username, User.class));
    }

    @Override
    public List<User> getAllUsers (){
        return userRepository.findAll();
    }

    @Override
    public void deleteUser (Long id){
        userRepository.deleteById(id);
    }
    //update w/o userName and password
   
    @Override
    public User updateUser (Long id, User user){
        User updateUser = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, User.class));
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        return userRepository.save(updateUser);
    }

    
    //todo change password

}
