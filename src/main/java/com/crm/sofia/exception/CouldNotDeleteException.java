package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class CouldNotDeleteException extends SofiaException {

    public CouldNotDeleteException() {
    }

    public CouldNotDeleteException(String message) {
        super(message);
    }

    public CouldNotDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

}
