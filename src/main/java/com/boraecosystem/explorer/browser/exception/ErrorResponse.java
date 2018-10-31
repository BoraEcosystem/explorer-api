package com.boraecosystem.explorer.browser.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private String error;
    private int status;
    private String message;
}
