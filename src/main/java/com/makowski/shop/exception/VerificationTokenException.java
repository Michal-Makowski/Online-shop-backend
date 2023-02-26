package com.makowski.shop.exception;

public class VerificationTokenException extends RuntimeException{
    
    public VerificationTokenException(String token, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " : '" + token + "' is not valid");
    }

    public VerificationTokenException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' is expired");
    }
}
