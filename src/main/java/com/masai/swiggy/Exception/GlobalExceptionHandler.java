package com.masai.swiggy.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyError> myallHandler(Exception e){
        MyError err = new MyError(LocalDateTime.now().toString(),e.getMessage(), null);
        return new ResponseEntity<>(err, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(SwiggyException.class)
    public ResponseEntity<MyError> myallHandlerSwiggy(SwiggyException e){
        MyError err = new MyError(LocalDateTime.now().toString(),e.getMessage(), null);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyError> myallHandlerAllMethod(MethodArgumentNotValidException e, WebRequest req){
        MyError err = new MyError(LocalDateTime.now().toString(),"Validation Error", e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }




}
