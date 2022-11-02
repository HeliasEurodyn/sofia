package com.crm.sofia.exception.login;

import com.crm.sofia.exception.common.SofiaException;

public class ChangePasswordException extends SofiaException {

    public ChangePasswordException() {
        super("Passwords Do Not Match");
        this.setCategory("LOGIN");
        this.setCode("001-3");
        this.setVisible(true);
    }

    public ChangePasswordException(String message) {
        super(message);
        this.setCategory("LOGIN");
        this.setCode("001-3");
        this.setVisible(true);
    }

}
