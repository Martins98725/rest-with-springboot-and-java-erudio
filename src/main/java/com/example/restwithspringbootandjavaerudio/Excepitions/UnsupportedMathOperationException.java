package com.example.restwithspringbootandjavaerudio.Excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {
    private static final long serialVersionsUId = 1L;

    public UnsupportedMathOperationException(String ex) {
        super(ex);
    }
}
