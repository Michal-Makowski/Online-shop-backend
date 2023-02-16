package com.makowski.shop.entity.user;

import com.makowski.shop.validation.ValidationConstans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_delivery_address")
public class UserDeliveryAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @NotBlank(message = ValidationConstans.FIRST_NAME_NOT_BLANK)
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NonNull
    @NotBlank(message = ValidationConstans.LAST_NAME_NOT_BLANK)
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @NonNull
    @NotBlank(message = ValidationConstans.CITY_NOT_BLANK)
    @Column(name = "city", nullable = false)
    private String city;
    
    @NonNull
    @NotBlank(message = ValidationConstans.STREET_NOT_BLANK)
    @Column(name = "street", nullable = false)
    private String street;

    @NonNull
    @NotBlank(message = ValidationConstans.HOUSE_NUMBER_NOT_BLANK)
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @NonNull
    @NotBlank(message = ValidationConstans.POSTCODE_NOT_BLANK)
    @Column(name = "postcode", nullable = false)
    private String postcode;
}

