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

import com.makowski.shop.entity.user.UserDeliveryAddress;
import com.makowski.shop.security.SecurityConstants;
import com.makowski.shop.service.user.UserDeliveryAddressService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/userDeliveryAddress")
public class UserDeliveryAddressController {
    
    UserDeliveryAddressService userDeliveryAddressService;
    //TODO dont send password back
    @PostMapping("/user/{userId}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserDeliveryAddress> createUserDeliveryAddress(@Valid @RequestBody UserDeliveryAddress userDeliveryAddress, @PathVariable Long userId){
        return new ResponseEntity<>(userDeliveryAddressService.createUserDeliveryAddress(userDeliveryAddress, userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDeliveryAddress> getUserDeliveryAddressById(@PathVariable Long id){
        UserDeliveryAddress userDeliveryAddress = userDeliveryAddressService.getUserDeliveryAddressById(id);
        userDeliveryAddress.getUser().setPassword("XXXXXX");
        return new ResponseEntity<>(userDeliveryAddress, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserDeliveryAddress>> getAllUserDeliveryAddressByUserId(@PathVariable Long userId){
        List<UserDeliveryAddress> userDeliveryAddresses = userDeliveryAddressService.getAllUserDeliveryAddressByUserId(userId);
        userDeliveryAddresses.forEach(userDeliveryAddress -> userDeliveryAddress.getUser().setPassword("XXXXXX"));
        return new ResponseEntity<>(userDeliveryAddresses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserDeliveryAddress> deleteUserDeliveryAddress(@PathVariable Long id){
        userDeliveryAddressService.deleteUserDeliveryArdress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //TODO dont send user password back
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserDeliveryAddress> updateUserDeliveryAddrres (@PathVariable Long id, @Valid @RequestBody UserDeliveryAddress userDeliveryAddress){
        return new ResponseEntity<>(userDeliveryAddressService.updatUserDeliveryAddress(id, userDeliveryAddress), HttpStatus.OK);
    }
}
