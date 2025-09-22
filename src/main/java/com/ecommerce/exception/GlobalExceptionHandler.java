package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<String> handleObjectNotFound(ObjectNotFound  obj){
        return  new ResponseEntity<>(obj.getMessage(), HttpStatus.NOT_FOUND);
    }
}
