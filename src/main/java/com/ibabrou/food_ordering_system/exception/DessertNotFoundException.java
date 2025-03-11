package com.ibabrou.food_ordering_system.exception;

public class DessertNotFoundException extends RuntimeException {
    public DessertNotFoundException(String message) {
        super(message);
    }
}
