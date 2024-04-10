package com.example.anianetworkproject.infrastructure.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "loans", schema = "library")
public class LoanEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "loan_date", nullable = false)
    @Basic
    private Date loanDate;

    @Column(name = "due_date", nullable = false)
    @Basic
    private Date dueDate;

    @Column(name = "return_date")
    @Basic
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity bookId) {
        this.book = bookId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userId) {
        this.user = userId;
    }

    public long getId() {return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
