package com.makowski.shop.service.user;

public interface EmailSenderService {
    void sendEmail(String toEmail, String token);

}
