package com.ecommerce.exception;

import java.security.PublicKey;

public class ObjectNotFound extends  RuntimeException{
    public  ObjectNotFound(String message){
        super(message);
    }
}
