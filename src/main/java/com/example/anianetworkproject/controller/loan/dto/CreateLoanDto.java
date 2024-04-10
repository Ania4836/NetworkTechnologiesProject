package com.example.anianetworkproject.controller.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class CreateLoanDto {
    @NotNull
    @Schema(name = "dueDate", example = "2021-12-31")
    private Date dueDate;

    @NotNull
    @Schema(name = "userId", example = "1")
    private Long userId;

    @NotNull
    @Schema(name = "bookId", example = "1")
    private Long bookId;

    public CreateLoanDto() {
    }

    public CreateLoanDto(Date dueDate, Long userId, Long bookId) {
        this.dueDate = dueDate;
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}
