package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class DisabledException extends SofiaException {
    public DisabledException() {
    }

    public DisabledException(String message) {
        super(message);
    }

    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisabledException(String message, Object... args) {
        super(message, args);
    }
}
