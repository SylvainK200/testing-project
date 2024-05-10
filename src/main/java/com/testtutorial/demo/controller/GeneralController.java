package com.testtutorial.demo.controller;

import exceptionhandler.exceptions.ExceptionResponse;
import exceptionhandler.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class GeneralController {
    @ExceptionHandler(value = {ObjectNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleException(ObjectNotFoundException exception) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }
}
