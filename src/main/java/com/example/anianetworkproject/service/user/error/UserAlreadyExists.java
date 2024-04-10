package com.example.anianetworkproject.service.user.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExists {
    public static ResponseStatusException create (String username) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with username: %s already exists.", username));
    }
}
