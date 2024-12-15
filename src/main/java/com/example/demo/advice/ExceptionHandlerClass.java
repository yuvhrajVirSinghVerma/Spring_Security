package com.example.demo.advice;

import com.example.demo.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerClass{

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError>AuthenticationExceptionHandler(AuthenticationException ex){
        ApiError error=ApiError.builder().message(ex.getLocalizedMessage()).status(HttpStatus.UNAUTHORIZED).build();
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError>AJwtExceptionHandler(JwtException ex){
        System.out.println("jwt global exception handler");
        ApiError error=ApiError.builder().message(ex.getLocalizedMessage()).status(HttpStatus.UNAUTHORIZED).build();
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError>ResourceNotFoundException(ResourceNotFoundException ex){
        System.out.println("ResourceNotFoundException exception handler");
        ApiError error=ApiError.builder().message(ex.getLocalizedMessage()).status(HttpStatus.UNAUTHORIZED).build();
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

}
