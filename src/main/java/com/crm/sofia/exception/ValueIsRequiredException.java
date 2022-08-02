package com.crm.sofia.exception;

public class ValueIsRequiredException extends SofiaException {

    public ValueIsRequiredException() {
    }

    public ValueIsRequiredException(String message) {
        super(message);
    }

    public ValueIsRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueIsRequiredException(String message, Object... args) {
        super(message, args);
    }

}
