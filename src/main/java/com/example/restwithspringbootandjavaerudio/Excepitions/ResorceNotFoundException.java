package com.example.restwithspringbootandjavaerudio.Excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResorceNotFoundException extends RuntimeException {
    private static final long serialVersionsUId = 1L;

    public ResorceNotFoundException(String ex) {
        super(ex);
    }
}
