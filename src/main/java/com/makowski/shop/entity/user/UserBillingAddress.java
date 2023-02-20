package com.makowski.shop.entity.user;

import com.makowski.shop.validation.ValidationConstans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_billing_address")
public class UserBillingAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Size(min = 2, max = 25, message = ValidationConstans.NOT_SIZE)
    @Pattern(regexp = ValidationConstans.PATTERN_AZ, message = ValidationConstans.NO_MATCH_PATTERN_AZ)
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NonNull
    @Size(min = 2, max = 25, message = ValidationConstans.NOT_SIZE)
    @Pattern(regexp = ValidationConstans.PATTERN_AZ, message = ValidationConstans.NO_MATCH_PATTERN_AZ)
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @NonNull
    @Size(min = 2, max = 50, message = ValidationConstans.NOT_SIZE)
    @Pattern(regexp = ValidationConstans.PATTERN_AZ, message = ValidationConstans.NO_MATCH_PATTERN_AZ)
    @Column(name = "city", nullable = false)
    private String city;
    
    @NonNull
    @Size(min = 2, max = 50, message = ValidationConstans.NOT_SIZE)
    @Pattern(regexp = ValidationConstans.PATTERN_AZ09, message = ValidationConstans.NO_MATCH_PATTERN_AZ09)
    @Column(name = "street", nullable = false)
    private String street;

    @NonNull
    @Size(min = 1, max = 25, message = ValidationConstans.NOT_SIZE)
    @Pattern(regexp = ValidationConstans.PATTERN_AZ09, message = ValidationConstans.NO_MATCH_PATTERN_AZ09)
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @NonNull
    @Size(min = 1, max = 25, message = ValidationConstans.NOT_SIZE)
    @Pattern(regexp = ValidationConstans.PATTERN_AZ09, message = ValidationConstans.NO_MATCH_PATTERN_AZ09)
    @Column(name = "postcode", nullable = false)
    private String postcode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
