package org.eme.petrestfulapp.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {
    protected ApiException(String message) {
        super(message);
    }

    protected abstract HttpStatus getHttpStatus();
}
