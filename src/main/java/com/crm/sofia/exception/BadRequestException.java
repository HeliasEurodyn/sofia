package com.crm.sofia.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 752858877580882829L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class AlreadyExistsException extends SofiaException {

        public AlreadyExistsException() {
        }

        public AlreadyExistsException(String message) {
            super(message);
        }

        public AlreadyExistsException(String message, Throwable cause) {
            super(message, cause);
        }

        public AlreadyExistsException(String message, Object... args) {
            super(message, args);
        }

    }

    public static class AuthenticationException extends SofiaException {

        public AuthenticationException() {
        }

        public AuthenticationException(String msg) {
            super(msg);
        }

        public AuthenticationException(String msg, Object... args) {
            super(msg, args);
        }

    }
}
