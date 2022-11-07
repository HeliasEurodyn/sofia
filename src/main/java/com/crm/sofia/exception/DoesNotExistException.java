package com.crm.sofia.exception;

import com.crm.sofia.exception.common.SofiaException;

public class DoesNotExistException extends SofiaException {

    public DoesNotExistException() {
        super("Object Does Not Exist");
        this.setCode("002-1");
        this.setCategory("OBJECT_NOT_FOUND");
        this.setVisible(true);
    }

    public DoesNotExistException(String message) {
        super(message);
        this.setCode("002-1");
        this.setCategory("OBJECT_NOT_FOUND");
        this.setVisible(true);
    }

}
