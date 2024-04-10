package com.example.anianetworkproject.controller.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.openapitools.jackson.nullable.JsonNullable;

import java.sql.Date;

public class PatchUserDto {

    @NotBlank (message = "Name is mandatory")
    @Schema(name = "name", example = "name")
    private JsonNullable<String> name;
    @NotBlank (message = "Last name is mandatory")
    @Schema(name = "lastName", example = "lastName")
    private JsonNullable<String> lastName;
    @Schema(name = "dateOfBirth", example = "2021-12-31")
    private JsonNullable<Date> dateOfBirth;
    @NotBlank (message = "Email is mandatory")
    @Schema(name = "email", example = "email@email.com")
    private JsonNullable<String> email;

    public PatchUserDto(JsonNullable<String> name, JsonNullable<String> lastName, JsonNullable<Date> dateOfBirth, JsonNullable<String> email) {
        this.email = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.name = email;
    }

    public JsonNullable<String> getName() {
        return name;
    }

    public void setName(JsonNullable<String> name) {
        this.name = name;
    }

    public JsonNullable<String> getLastName() {
        return lastName;
    }

    public void setLastName(JsonNullable<String> lastName) {
        this.lastName = lastName;
    }

    public JsonNullable<Date> getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(JsonNullable<Date> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public JsonNullable<String> getEmail() {
        return email;
    }

    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }
}