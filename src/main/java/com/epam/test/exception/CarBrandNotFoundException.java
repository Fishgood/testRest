package com.epam.test.exception;

public class CarBrandNotFoundException extends RuntimeException {

    public CarBrandNotFoundException(Long id) {
        super("Could not find student with id: " + id);
    }
}
