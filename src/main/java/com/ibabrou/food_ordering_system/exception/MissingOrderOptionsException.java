package com.ibabrou.food_ordering_system.exception;

public class MissingOrderOptionsException extends RuntimeException {
    public MissingOrderOptionsException(String message) {
        super(message);
    }
}
