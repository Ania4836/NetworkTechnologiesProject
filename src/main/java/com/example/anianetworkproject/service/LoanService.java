package com.example.anianetworkproject.service;

import com.example.anianetworkproject.infrastructure.entity.LoanEntity;
import com.example.anianetworkproject.infrastructure.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<LoanEntity> getAllLoans() {
        return loanRepository.findAll();
    }

    public LoanEntity getLoanById(long id) {
        return loanRepository.findById(id).orElse(null);
    }

    public LoanEntity createLoan(LoanEntity loan) {
        return loanRepository.save(loan);
    }

    public void deleteLoan(long id) {
        loanRepository.deleteById(id);
    }

}
