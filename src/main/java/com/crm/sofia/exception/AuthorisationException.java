package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class AuthorisationException extends SofiaException {

    public AuthorisationException() {
    }

    public AuthorisationException(String msg) {
        super(msg);
    }

    public AuthorisationException(String msg, Object... args) {
        super(msg, args);
    }

}
