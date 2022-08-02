package com.crm.sofia.exception.handler;

import com.crm.sofia.exception.ExpressionException;
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

}
