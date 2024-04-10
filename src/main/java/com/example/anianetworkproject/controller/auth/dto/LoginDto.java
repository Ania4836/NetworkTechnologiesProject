package com.example.anianetworkproject.controller.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank(message = "Username is mandatory")
    @Schema(name = "username", example = "username")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Schema(name = "password", example = "password")
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
