package org.eme.petrestfulapp.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> handleApiException(ApiException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, ex.getHttpStatus());
    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> handleOtherApiException(Exception ex, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                  HttpHeaders headers,
                                 HttpStatus status,
                                 WebRequest request) {
        ValidationError validationError = new ValidationError();
        validationError.setUri(request.getDescription(false));
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors){
            List<String> errors = validationError.getErrors().get(fieldError.getField());
            if(errors ==null){
                errors = new ArrayList<>(0);
            }
            errors.add(fieldError.getDefaultMessage());
            validationError.getErrors().put(fieldError.getField(),errors);
//            validationError.getFieldErrors().add(fieldError.getDefaultMessage());
        }

//        ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .forEach(validationError::addError);
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }
}