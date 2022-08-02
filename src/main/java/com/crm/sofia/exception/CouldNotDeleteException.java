package com.crm.sofia.exception;

public class CouldNotDeleteException extends SofiaException{

    public CouldNotDeleteException() {
    }

    public CouldNotDeleteException(String message) {
        super(message);
    }

    public CouldNotDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

}
