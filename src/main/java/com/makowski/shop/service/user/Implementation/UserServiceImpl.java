package com.makowski.shop.service.user.Implementation;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.makowski.shop.dto.UserDto;
import com.makowski.shop.entity.user.Role;
import com.makowski.shop.entity.user.User;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.exception.PasswordNotMatchException;
import com.makowski.shop.repository.user.UserRepository;
import com.makowski.shop.security.MySecurityContextHolder;
import com.makowski.shop.service.user.UserCartService;
import com.makowski.shop.service.user.UserFavoriteProductsService;
import com.makowski.shop.service.user.UserLastProductsService;
import com.makowski.shop.service.user.UserService;
import com.makowski.shop.service.user.VerificationTokenService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserCartService userCartService;
    private UserFavoriteProductsService userFavoriteProductsService;
    private UserLastProductsService userLastProductsService;
    private MySecurityContextHolder mySecurityContextHolder;
    private VerificationTokenService verificationTokenService;

    public User createCustomer(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
        userCartService.createUserCart(user);
        userFavoriteProductsService.createUserFavoriteProducts(user);
        userLastProductsService.createUserLastProducts(user);
        return user;
    }

    @Override
    public User createEmployee(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.EMPLOYEE);
        user.setEnabled(false);
        userRepository.save(user);
        verificationTokenService.createVerificationToken(user);
        return user;
    }

    @Override
    public User confirmUser(String token) {
        Long userId = verificationTokenService.checkVerificationToken(token);
        User user = getUserById(userId);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public User createAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    } 

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(username, User.class));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        mySecurityContextHolder.userIsValid(id);
        userRepository.deleteById(id);
    }
    // update w/o userName and password

    @Override
    public User updateUser(Long id, User user) {
        mySecurityContextHolder.userIsValid(id);
        User updateUser = getUserById(id);
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        return userRepository.save(updateUser);
    }

    @Override
    public User changePassword(String username, UserDto userDto) {
        mySecurityContextHolder.userIsValid(username);
        User user = getUserByUsername(username);
        if (userDto.getNewPassword().equals(userDto.getRepeatNewPassword())) {
            if (bCryptPasswordEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(userDto.getNewPassword()));
            } else {
                throw new PasswordNotMatchException("Wrong password");
            }
        } else {
            throw new PasswordNotMatchException("newPassword and repeatNewPassword does not match");
        }
        return userRepository.save(user);

    }

}
