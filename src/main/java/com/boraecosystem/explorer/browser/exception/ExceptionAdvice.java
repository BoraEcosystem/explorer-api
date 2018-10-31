package com.boraecosystem.explorer.browser.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidAddressException.class})
    public ResponseEntity<Object> handleInvalidAddressException(InvalidAddressException e, WebRequest request) {
        return handleExceptionInternal(e, badRequest(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {

        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strs = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strs.append(violation.getMessage());
        }

        return handleExceptionInternal(e, badRequest(strs.toString()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ErrorResponse badRequest(String message) {
        return ErrorResponse.builder()
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(message)
            .status(HttpStatus.BAD_REQUEST.value())
            .build();
    }

}
