package com.ecommerce.autorisation.exceptions;

public class LoginAuthenticationException extends RuntimeException{
    public LoginAuthenticationException(String message) {
        super(message);
    }
}
