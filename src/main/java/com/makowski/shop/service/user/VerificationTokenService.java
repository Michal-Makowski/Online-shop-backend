package com.makowski.shop.service.user;

import com.makowski.shop.entity.user.User;

public interface VerificationTokenService {
    void createVerificationToken(User user);
    Long checkVerificationToken(String token);
}
