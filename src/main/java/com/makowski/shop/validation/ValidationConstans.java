package com.makowski.shop.validation;

public class ValidationConstans { 
    public static final String NOT_BLANK = "cannot be blank";
    public static final String EMAIL_NOT_VALID = "is not valid. Please enter a valid email";
    public static final String NOT_NULL = "cannot be null";
    public static final String NOT_MIN = "cannot be negative";
    public static final String NOT_SIZE = "'${validatedValue}' must be between {min} and {max} characters long";
    
    public static final String NO_MATCH_PATTERN_AZ = "cannot have special character or digits";
    public static final String NO_MATCH_PATTERN_USERNAME = "cannot have special character";
    public static final String NO_MATCH_PATTERN_AZ09 = "cannot have special character except ' - ' and ' ' '";
    public static final String NO_MATCH_PATTERN_09 = "can contain only digits";
    
    public static final String PATTERN_AZ = "^[a-zA-Z-']*$";
    public static final String PATTERN_USERNAME = "^[a-zA-Z0-9]*$";
    public static final String PATTERN_AZ09 = "^[a-zA-Z0-9-']*$";
    public static final String PATTERN_09 = "^[0-9.]*$";
}
