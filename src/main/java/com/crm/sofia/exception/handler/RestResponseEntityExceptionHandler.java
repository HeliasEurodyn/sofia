package com.crm.sofia.exception.handler;

import com.crm.sofia.exception.ExpressionException;
import com.crm.sofia.exception.login.IncorrectPasswordException;
import com.crm.sofia.exception.login.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler{

    @ExceptionHandler(ExpressionException.class)
    public ResponseEntity<Map<String,String>> handleExprException(ExpressionException exprException){
        Map<String,String> response = new HashMap<>();
        response.put("code", "4001");
        response.put("message", exprException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        Map<String,String> response = new HashMap<>();
        response.put("code", userNotFoundException.getCode());
        response.put("message", userNotFoundException.getMessage());
        response.put("category",userNotFoundException.getCategory());
        response.put("isVisible",Boolean.toString(userNotFoundException.isVisible()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Map<String,String>> handleIncorrectPasswordException(IncorrectPasswordException incorrectPasswordException){
        Map<String,String> response = new HashMap<>();
        response.put("code", incorrectPasswordException.getCode());
        response.put("message", incorrectPasswordException.getMessage());
        response.put("category",incorrectPasswordException.getCategory());
        response.put("isVisible",Boolean.toString(incorrectPasswordException.isVisible()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
