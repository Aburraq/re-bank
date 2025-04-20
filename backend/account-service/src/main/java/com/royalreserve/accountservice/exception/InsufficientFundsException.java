package com.royalreserve.accountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String id) {
        super("Insufficient funds in account with id: " + id);
    }
}
