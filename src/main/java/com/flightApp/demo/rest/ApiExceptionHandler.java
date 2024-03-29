package com.flightApp.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {


    // handle all in ones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(HttpServletRequest req, Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an error accured, please try again");
    }
}
