package com.makowski.shop.dto;

import com.makowski.shop.validation.Password;
import com.makowski.shop.validation.ValidationConstans;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserDto {
    
    @NonNull
    @NotBlank(message = ValidationConstans.NOT_BLANK)
    @Password
    String newPassword;
    @NonNull
    String repeatNewPassword;
    @NonNull
    String oldPassword;
    
}
