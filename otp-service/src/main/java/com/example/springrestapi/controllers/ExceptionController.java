package com.example.springrestapi.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ;
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        if (ex.getStatus() == HttpStatus.BAD_REQUEST) {
            String reason = ex.getReason();
            return new ResponseEntity<Object>(reason, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(ex, ex.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(e -> {
            errors.put(e.getPropertyPath().toString(), e.getMessage());
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ServletException.class)
    protected ResponseEntity<Object> handleServletException(ServletException ex) {

        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
