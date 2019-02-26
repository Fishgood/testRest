package com.epam.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CarBrandNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CarBrandNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String carBrandNotFoundHandler(CarBrandNotFoundException ex) {
        return ex.getMessage();
    }
}
