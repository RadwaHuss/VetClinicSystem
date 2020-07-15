package org.eme.petrestfulapp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

public class ValidationError {

    Map<String,List<String>> errors = new HashMap<>(0);
    private String uri;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
    private Date timestamp;

    public ValidationError() {
        this.timestamp = new Date();
    }


    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
