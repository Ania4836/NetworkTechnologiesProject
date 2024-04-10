package com.example.anianetworkproject.controller.user.dto;

import java.sql.Date;

public class GetUserDto {
    private long id;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private String email;

    public GetUserDto() {
    }

    public GetUserDto(long id, String name, String lastName, Date dateOfBirth, String email) {
        this.id = id;
        this.email = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.name = email;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
