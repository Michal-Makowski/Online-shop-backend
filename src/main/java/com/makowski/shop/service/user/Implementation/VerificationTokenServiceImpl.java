package com.makowski.shop.service.user.Implementation;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.VerificationToken;
import com.makowski.shop.exception.VerificationTokenException;
import com.makowski.shop.repository.user.VerificationTokenRepository;
import com.makowski.shop.service.user.EmailSenderService;
import com.makowski.shop.service.user.VerificationTokenService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService{

    private VerificationTokenRepository verificationTokenRepository;
    private EmailSenderService emailSenderService;

    @Override
    public void createVerificationToken (User user){
        
        VerificationToken token = new VerificationToken();
            token.setToken(UUID.randomUUID().toString());
            token.setUser(user);
            token.setExpiredAt(LocalDateTime.now().plusMinutes(20));
        verificationTokenRepository.save(token);
        emailSenderService.sendEmail(user.getEmail(), token.getToken());
        
    }

    @Override
    public Long checkVerificationToken(String token) {
        
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
            .orElseThrow(() -> new VerificationTokenException(token, VerificationToken.class));
        if(verificationToken.getExpiredAt().isAfter(LocalDateTime.now())){
            return verificationToken.getUser().getId();
        }else{
            throw new VerificationTokenException(verificationToken.getId(), VerificationToken.class);
        }
    }

}
