package com.crm.sofia.exception.login;

import com.crm.sofia.exception.common.SofiaException;

public class IncorrectPasswordException extends SofiaException {

    public IncorrectPasswordException() {
        this.setCategory("LOGIN");
        this.setMessage("Incorrect Password");
        this.setCode("001-1");
        this.setVisible(true);
    }

    public IncorrectPasswordException(String message) {
        super(message);
        this.setCategory("LOGIN");
        this.setCode("001-1");
        this.setVisible(true);
    }

}
