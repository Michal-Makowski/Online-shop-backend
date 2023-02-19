package com.makowski.shop.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "w!z%C*F-J@NcRfUjXn2r5u8x/A?D(G+KbPdSgVkYp3s6v9y$B&E)H@McQfThWmZq"; 
    public static final int TOKEN_EXPIRATION = 1200000; // 20 min
    public static final String BEARER = "Bearer "; 
    public static final String AUTHORIZATION = "Authorization"; 
    public static final String REGISTER_PATH = "/user/register"; 
    public static final String CUSTOMER = "CUSTOMER";
    public static final String ADMIN = "ADMIN";
    public static final String EMPLOYEE = "EMPLOYEE";
}
