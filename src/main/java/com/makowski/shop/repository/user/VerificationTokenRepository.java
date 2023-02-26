package com.makowski.shop.repository.user;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.makowski.shop.entity.user.VerificationToken;


public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken, Long>{

    Optional<VerificationToken> findByToken(String token);
    
}
