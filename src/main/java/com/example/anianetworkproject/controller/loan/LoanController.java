package com.example.anianetworkproject.controller.loan;


import com.example.anianetworkproject.controller.loan.dto.CreateLoanDto;
import com.example.anianetworkproject.controller.loan.dto.CreateLoanResponseDto;
import com.example.anianetworkproject.controller.loan.dto.GetLoanResponseDto;
import com.example.anianetworkproject.controller.loan.dto.GetLoansPageResponseDto;
import com.example.anianetworkproject.service.loan.LoanService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/loans")
@PostAuthorize("isAuthenticated()")

public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan found successfully"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    public ResponseEntity<GetLoanResponseDto> getOneById(@PathVariable long id) {
        GetLoanResponseDto dto = loanService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loans found successfully"),
            @ApiResponse(responseCode = "404", description = "Loans not found")
    })
    public ResponseEntity<GetLoansPageResponseDto> getAll(@RequestParam(required = false) Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        GetLoansPageResponseDto dto = loanService.getAll(userId, page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loan created successfully"),
            @ApiResponse(responseCode = "400", description = "Loan creation failed")
    })
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody @Validated CreateLoanDto loan) {
        var newLoan = loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Loan deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Loan not found")
    })
    public ResponseEntity<Void> delete(@PathVariable long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}