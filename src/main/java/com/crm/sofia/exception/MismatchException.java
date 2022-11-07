package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class MismatchException extends SofiaException {
    public MismatchException() {
    }

    public MismatchException(String message) {
        super(message);
    }

    public MismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public MismatchException(String message, Object... args) {
        super(message, args);
    }
}
