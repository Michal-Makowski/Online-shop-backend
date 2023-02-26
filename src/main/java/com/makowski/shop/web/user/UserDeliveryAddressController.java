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

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "1.2 User Delivery Address")
@AllArgsConstructor
@RestController
@RequestMapping("/userDeliveryAddress")
public class UserDeliveryAddressController {
    
    private UserDeliveryAddressService userDeliveryAddressService;
    
    @PostMapping("/user/{userId}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserDeliveryAddress> createUserDeliveryAddress(@Valid @RequestBody UserDeliveryAddress userDeliveryAddress, @PathVariable Long userId){
        return new ResponseEntity<>(userDeliveryAddressService.createUserDeliveryAddress(userDeliveryAddress, userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.CUSTOMER +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<UserDeliveryAddress> getUserDeliveryAddressById(@PathVariable Long id){
        return new ResponseEntity<>(userDeliveryAddressService.getUserDeliveryAddressById(id), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('" + SecurityConstants.CUSTOMER +"', '" + SecurityConstants.ADMIN +"')")
    public ResponseEntity<List<UserDeliveryAddress>> getAllUserDeliveryAddressByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(userDeliveryAddressService.getAllUserDeliveryAddressByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserDeliveryAddress> deleteUserDeliveryAddress(@PathVariable Long id){
        userDeliveryAddressService.deleteUserDeliveryArdress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('" + SecurityConstants.CUSTOMER +"')")
    public ResponseEntity<UserDeliveryAddress> updateUserDeliveryAddrres (@PathVariable Long id, @Valid @RequestBody UserDeliveryAddress userDeliveryAddress){
        return new ResponseEntity<>(userDeliveryAddressService.updatUserDeliveryAddress(id, userDeliveryAddress), HttpStatus.OK);
    }
}
