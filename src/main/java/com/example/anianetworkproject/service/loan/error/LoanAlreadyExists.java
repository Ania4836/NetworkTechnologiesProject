package com.example.anianetworkproject.service.loan.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoanAlreadyExists {
    public static ResponseStatusException create (long bookId, long userId) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("Loan with user ID: %s and book ID: %s already exists.", userId, bookId));
    }
}
