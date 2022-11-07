package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class SecurityException extends SofiaException {
    public SecurityException() {
    }

    public SecurityException(String msg) {
        super(msg);
    }

    public SecurityException(String msg, Object... args) {
        super(msg, args);
    }
}
