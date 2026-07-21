package com.push.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateMobileNumberFoundException extends RuntimeException{
    public DuplicateMobileNumberFoundException(String message) {
        super(message);
    }
}
