package com.avaliacao.backend.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.avaliacao.backend.exception.ArgumentException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ArgumentException.class)
    public ResponseEntity<String> handleInvalidCpfException(ArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}