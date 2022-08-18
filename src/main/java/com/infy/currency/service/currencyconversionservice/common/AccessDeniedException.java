package com.infy.currency.service.currencyconversionservice.common;

public class AccessDeniedException extends RuntimeException{
    
    public AccessDeniedException(String message){
        super(message);
    }
    
}
