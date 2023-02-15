package com.makowski.shop.entity.user;

import com.makowski.shop.validation.ValidationConstans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    
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
    @NotBlank(message = ValidationConstans.USERNAME_NOT_BLANK)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NonNull
    @Email(message = ValidationConstans.EMAIL_NOT_VALID)
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @NonNull
    @NotBlank(message = ValidationConstans.PASSWORD_NOT_BLANK)
    //todo password validation
    @Column(name = "password", nullable = false)
    private String password;

}
