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

import com.makowski.shop.entity.user.UserBillingAddress;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.user.UserBillingAddressService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "1.3 User Billing Address")
@AllArgsConstructor
@RestController
@RequestMapping("/userBillingAddress")
public class UserBillingAddressController {
    
    private UserBillingAddressService userBillingAddressService;

    @PostMapping("/user/{userId}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserBillingAddress> createUserBillingAddress(@Valid @RequestBody UserBillingAddress userBillingAddress, @PathVariable Long userId){
        return new ResponseEntity<>(userBillingAddressService.createUserBillingAddress(userBillingAddress, userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.CUSTOMER +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<UserBillingAddress> getUserBillingAddressById(@PathVariable Long id){
        return new ResponseEntity<>(userBillingAddressService.getUserBillingAddressById(id), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.CUSTOMER +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<List<UserBillingAddress>> getAllUserBillingAddressByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(userBillingAddressService.getAllUserBillingAddressByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserBillingAddress> deleteUserBillingAddress(@PathVariable Long id){
        userBillingAddressService.deleteUserBillingArdress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserBillingAddress> updateUserBillingAddrres (@PathVariable Long id, @Valid @RequestBody UserBillingAddress userBillingAddress){
        return new ResponseEntity<>(userBillingAddressService.updatUserBillingAddress(id, userBillingAddress), HttpStatus.OK);
    }
}
