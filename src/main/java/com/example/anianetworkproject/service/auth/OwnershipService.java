package com.example.anianetworkproject.service.auth;


import com.example.anianetworkproject.infrastructure.entity.AuthEntity;
import com.example.anianetworkproject.infrastructure.repository.AuthRepository;
import com.example.anianetworkproject.service.user.error.UserNotFound;

public abstract class OwnershipService {
    protected final AuthRepository authRepository;

    public OwnershipService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean isOwner(String username, Long userId) {
        if (userId == null || username == null) {
            return false;
        }

        AuthEntity auth = authRepository
                .findByUsername(username)
                .orElseThrow(() -> UserNotFound.createWithUsername(username));

        return userId == auth.getUser().getId();
    }
}