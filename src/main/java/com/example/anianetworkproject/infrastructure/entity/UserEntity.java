package com.example.anianetworkproject.infrastructure.entity;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users", schema = "library")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Basic
    private String name;

    @Column(name = "last_name")
    @Basic
    private String lastName;

    @Column(name = "date_of_birth")
    @Basic
    private Date dateOfBirth;

    @Column(name = "email", nullable = false)
    @Basic
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LoanEntity> loans;

    public long getId() {
        return id;
    }

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

    public void setName(String name) {
        this.name = name;
    }

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

    public List<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
    }
}
