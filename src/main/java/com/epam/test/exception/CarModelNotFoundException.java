package com.epam.test.exception;

public class CarModelNotFoundException extends RuntimeException {

    public CarModelNotFoundException(Long id) {
        super("Could not find car model with id: " + id);
    }
}
