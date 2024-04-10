package com.example.anianetworkproject.service.book.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFound {
    public static ResponseStatusException create(long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id: %s not found", id));
    }
}
