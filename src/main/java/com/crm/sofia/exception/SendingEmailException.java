package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class SendingEmailException extends SofiaException {

    public SendingEmailException() {
        super("Configuration is wrong");
        this.setCategory("EMAIL");
        this.setCode("001-5");
        this.setVisible(true);
    }

    public SendingEmailException(String message) {
        super(message);
        this.setCategory("EMAIL");
        this.setCode("001-5");
        this.setVisible(true);
    }

}
