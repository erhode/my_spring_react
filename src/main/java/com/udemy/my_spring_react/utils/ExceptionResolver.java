package com.udemy.my_spring_react.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //catch toute les exceptions
public class ExceptionResolver {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = error.getObjectName();
            String errorDefaultMessage = error.getDefaultMessage();

            errors.put(fieldName, error.getDefaultMessage());
        });

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
}
