package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class DoesNotExistException extends SofiaException {

    public DoesNotExistException() {
    }

    public DoesNotExistException(String message) {
        super(message);
    }

    public DoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoesNotExistException(String message, Object... args) {
        super(message, args);
    }

}
