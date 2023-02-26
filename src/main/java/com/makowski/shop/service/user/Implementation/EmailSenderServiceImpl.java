package com.makowski.shop.service.user.Implementation;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.makowski.shop.service.user.EmailSenderService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService{
    
    private JavaMailSender mailSender;
    private static final String VERIFICATION_PATH = "/user/verificationToken/";
    
    

    public void sendEmail(String toEmail, String token){
        final String url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String tokenVerificationUrl = url + VERIFICATION_PATH + token; 
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("makowski.michal92@gmail.com");
        message.setTo(toEmail);
        message.setText("your verification link : " + tokenVerificationUrl +"");
        message.setSubject("Email Verification");
        mailSender.send(message);
        
    }
    
}
