package com.example.restwithspringbootandjavaerudio.Excepitions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequeiredObjectIsNullException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RequeiredObjectIsNullException() {
        super("It is allowed to persist a null object");
    }

}
