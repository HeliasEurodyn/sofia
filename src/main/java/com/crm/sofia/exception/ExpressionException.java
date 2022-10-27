package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class ExpressionException extends SofiaException {

    public ExpressionException(String message) {
        super(message);
    }

    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionException(String message, Object... args) {
        super(message, args);
    }
}

