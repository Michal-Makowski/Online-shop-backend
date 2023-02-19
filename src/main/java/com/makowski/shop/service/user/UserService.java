package com.makowski.shop.service.user;

import java.util.List;

import com.makowski.shop.entity.user.User;

public interface UserService {
    User createCustomer(User user);
    User createAdmin(User user);
    User createEmployee(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User updateUser(Long id , User user);
}
