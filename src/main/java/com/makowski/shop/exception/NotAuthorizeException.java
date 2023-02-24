package com.makowski.shop.exception;

public class NotAuthorizeException extends RuntimeException{
    
    public NotAuthorizeException(String username) {
        super("The User with username " + username + " can change or get only his own data");
    }

    public NotAuthorizeException(String username, Class<?> entity) {
        super("The User with username " + username + " can edit or delete only his own " + entity.getSimpleName().toLowerCase() + "");
    }
}
