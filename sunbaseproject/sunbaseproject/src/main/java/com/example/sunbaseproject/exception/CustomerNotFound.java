package com.example.sunbaseproject.exception;

public class CustomerNotFound extends RuntimeException{

    public CustomerNotFound(String msg){
        super(msg);
    }
}
