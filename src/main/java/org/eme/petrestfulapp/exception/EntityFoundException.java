package org.eme.petrestfulapp.exception;

import org.springframework.http.HttpStatus;

public class EntityFoundException extends  ApiException {
    public EntityFoundException(String message) {
        super(message);
    }

    @Override
    protected HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
