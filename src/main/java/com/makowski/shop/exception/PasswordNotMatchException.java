package com.makowski.shop.exception;

public class PasswordNotMatchException extends RuntimeException{
    
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
