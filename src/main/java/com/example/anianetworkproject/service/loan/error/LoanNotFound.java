package com.example.anianetworkproject.service.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanNotFound {
    public static ResponseStatusException create(long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Loan with id: %s not found", id));
    }
}
