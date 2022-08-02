package com.crm.sofia.exception;

public class CouldNotSaveException extends SofiaException{

    public CouldNotSaveException() {
    }

    public CouldNotSaveException(String message) {
        super(message);
    }

    public CouldNotSaveException(String message, Throwable cause) {
        super(message, cause);
    }

}
