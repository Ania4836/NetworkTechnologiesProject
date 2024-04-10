package com.example.anianetworkproject.controller.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginResponseDto {


    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
