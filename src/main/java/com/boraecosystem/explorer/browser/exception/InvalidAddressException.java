package com.boraecosystem.explorer.browser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAddressException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Invalid Address. Check your address format.";

    public InvalidAddressException() {
        super(DEFAULT_MESSAGE);
    }

}
