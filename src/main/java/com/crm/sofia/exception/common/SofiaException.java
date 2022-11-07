package com.crm.sofia.exception.common;

import lombok.Data;

import java.io.Serializable;
import java.text.MessageFormat;

@Data
public class SofiaException extends RuntimeException implements Serializable {

    private String category;
    private String code;
    private boolean isVisible;

    protected SofiaException() {

    }

    protected SofiaException(String message) {
        super(message);
    }

    protected SofiaException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

    protected SofiaException(String message, Throwable cause) {
        super(message, cause);
    }

    protected SofiaException(Throwable cause) {
        super(cause);
    }

    protected SofiaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
