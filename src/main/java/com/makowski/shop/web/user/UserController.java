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

import com.makowski.shop.dto.UserDto;
import com.makowski.shop.entity.user.User;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.user.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;
    
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
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return  new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasRole('" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
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

    @PutMapping("/username/{username}")
    public ResponseEntity<UserDto> changePassword(@PathVariable String username, @RequestBody UserDto userDto){
        userService.changePassword(username, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
