package com.crm.sofia.exception;

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
