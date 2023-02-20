package com.makowski.shop.exception;

public class EntityNotFoundException extends RuntimeException{
    
    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }
    
    public EntityNotFoundException(String username, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with username '" + username + "' does not exist in our records");
    }

    public EntityNotFoundException(Long id, Class<?> subEntity, Class<?> entity) {
        super("The " + subEntity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records, or dont have " + entity.getSimpleName().toLowerCase() +" ( only user with role: customer have "+ entity.getSimpleName().toLowerCase() + " )");
    }
}
