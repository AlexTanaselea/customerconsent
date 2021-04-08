package com.alex.consent.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userInputException(IllegalArgumentException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
