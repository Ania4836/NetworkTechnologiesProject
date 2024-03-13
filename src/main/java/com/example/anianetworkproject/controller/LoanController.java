package com.example.anianetworkproject.controller;

import com.example.anianetworkproject.infrastructure.entity.LoanEntity;
import com.example.anianetworkproject.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<LoanEntity> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public LoanEntity getLoanById(@PathVariable long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    public LoanEntity createLoan(@RequestBody LoanEntity loan) {
        return loanService.createLoan(loan);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable long id) {
        loanService.deleteLoan(id);
    }

}
