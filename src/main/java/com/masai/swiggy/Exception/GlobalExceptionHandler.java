package com.masai.swiggy.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(SwiggyException.class)
    public ResponseEntity<MyError> myallHandlerSwiggy(SwiggyException e,WebRequest req){
        MyError err = new MyError(LocalDateTime.now().toString(),e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyError> myallHandlerAllMethod(MethodArgumentNotValidException e, WebRequest req){
        MyError err = new MyError(LocalDateTime.now().toString(),"Validation Error", e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

   // to handle Not found exception
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyError> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {


        MyError err=new MyError(LocalDateTime.now().toString(), nfe.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }






}
