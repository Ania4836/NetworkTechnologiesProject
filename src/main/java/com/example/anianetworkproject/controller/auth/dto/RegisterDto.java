package com.example.anianetworkproject.controller.auth.dto;

import com.example.anianetworkproject.commonTypes.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterDto {
    @NotBlank(message = "Username is mandatory")
    @Schema(name = "username", example = "username")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Schema(name = "password", example = "password")
    private String password;

    @NotNull
    @Schema(name = "role", example = "ROLE_ADMIN")
    private UserRole role;

    @NotBlank(message = "Email is mandatory")
    @Schema(name = "email", example = "email@email.com")
    @Email
    private String email;

    public RegisterDto(String username, String password, UserRole role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}