package com.crm.sofia.exception.login;

import com.crm.sofia.exception.common.SofiaException;

public class UserNotFoundException extends SofiaException {

    public UserNotFoundException() {
        this.setCategory("LOGIN");
        this.setMessage("User Not Found");
        this.setCode("001-1");
        this.setVisible(true);
    }

    public UserNotFoundException(String message) {
        super(message);
        this.setCategory("LOGIN");
        this.setCode("001-1");
        this.setVisible(true);
    }
}
