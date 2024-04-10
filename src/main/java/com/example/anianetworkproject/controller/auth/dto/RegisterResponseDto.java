package com.example.anianetworkproject.controller.auth.dto;


import com.example.anianetworkproject.commonTypes.UserRole;

public class RegisterResponseDto {

    private String username;

    private UserRole role;

    private long userId;

    public RegisterResponseDto(long userId, String username, UserRole role) {
        this.username = username;
        this.role = role;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
