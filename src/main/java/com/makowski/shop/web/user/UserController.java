package com.makowski.shop.web.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makowski.shop.entity.user.User;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.user.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    
    UserService userService;
    
    @PostMapping("/registerCustomer")
    public ResponseEntity<User> createCustomer(@Valid @RequestBody User user){
        userService.createCustomer(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/registerEmployee")
    public ResponseEntity<User> createEmployee(@Valid @RequestBody User user){
        userService.createEmployee(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<User> createAdmin(@Valid @RequestBody User user){
        userService.createAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        user.setPassword("XXXXXX");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        user.setPassword("XXXXXX");
        return  new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        users.forEach(user -> user.setPassword("XXXXXX"));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }
}
