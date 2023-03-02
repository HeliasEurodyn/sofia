package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;
import lombok.Builder;

public class EmptyRequiredFieldException extends SofiaException {

    public EmptyRequiredFieldException() {
        super("A Required Field is Empty");
        this.setCode("003-1");
        this.setCategory("EMPTY_REQUIRED_FIELD");
        this.setVisible(false);
    }

    public EmptyRequiredFieldException(String message) {
        super(message);
        this.setCode("003-1");
        this.setCategory("EMPTY_REQUIRED_FIELD");
        this.setVisible(false);
    }

}
