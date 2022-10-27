package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class ExpressionException extends SofiaException {

    public ExpressionException(String message) {
        super(message);
        this.setCode("4000");
        this.setCategory("4000");
        this.setVisible(true);
    }
}

