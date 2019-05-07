package com.jmorata.test2.controller;

import com.jmorata.test2.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAdvice {

    @ResponseBody
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String userHandler(UserException e) {
        return e.getLocalizedMessage();
    }

}
