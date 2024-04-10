package com.example.anianetworkproject.service.loan;


import com.example.anianetworkproject.controller.loan.dto.GetLoansPageResponseDto;
import com.example.anianetworkproject.controller.user.dto.GetUserDto;
import com.example.anianetworkproject.controller.book.dto.GetBookDto;
import com.example.anianetworkproject.controller.loan.dto.CreateLoanDto;
import com.example.anianetworkproject.controller.loan.dto.CreateLoanResponseDto;
import com.example.anianetworkproject.controller.loan.dto.GetLoanResponseDto;
import com.example.anianetworkproject.infrastructure.entity.LoanEntity;
import com.example.anianetworkproject.infrastructure.repository.AuthRepository;
import com.example.anianetworkproject.infrastructure.repository.BookRepository;
import com.example.anianetworkproject.infrastructure.repository.LoanRepository;
import com.example.anianetworkproject.infrastructure.repository.UserRepository;
import com.example.anianetworkproject.service.auth.OwnershipService;
import com.example.anianetworkproject.service.book.error.BookNotFound;
import com.example.anianetworkproject.service.loan.error.LoanAlreadyExists;
import com.example.anianetworkproject.service.loan.error.LoanNotFound;
import com.example.anianetworkproject.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService extends OwnershipService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository, AuthRepository authRepository) {
        super(authRepository);
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    @PostAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, returnObject.user.id)")
    public GetLoanResponseDto getOneById(long id) {
        LoanEntity loan = loanRepository
                .findById(id)
                .orElseThrow(() -> LoanNotFound.create(id));
        return mapLoan(loan);
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #userId)")
    public GetLoansPageResponseDto getAll(Long userId, int page, int size) {
        Page<LoanEntity> loansPage;

        Pageable pageable = PageRequest.of(page, size);

        if (userId == null) {
            loansPage = loanRepository.findAll(pageable);
        } else {
            loansPage = loanRepository.findByUserId(userId, pageable);
        }

        List<GetLoanResponseDto> loansDto = loansPage
                .stream()
                .map(this::mapLoan)
                .toList();

        return new GetLoansPageResponseDto(
                loansDto,
                loansPage.getNumber(),
                loansPage.getTotalElements(),
                loansPage.getTotalPages(),
                loansPage.hasNext());
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #loanDto.userId)")
    public CreateLoanResponseDto create(CreateLoanDto loanDto) {
        Optional<LoanEntity> existingLoan = loanRepository
                .findByBookIdAndUserId(loanDto.getBookId(), loanDto.getUserId());

        if (existingLoan.isPresent()) {
            throw LoanAlreadyExists.create(loanDto.getBookId(), loanDto.getUserId());
        }
        var book = bookRepository
                .findById(loanDto.getBookId())
                .orElseThrow(() -> BookNotFound.create(loanDto.getBookId()));
        var user = userRepository
                .findById(loanDto.getUserId())
                .orElseThrow(() -> UserNotFound.createWithId(loanDto.getUserId()));
        LoanEntity loan = new LoanEntity();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(new Date(System.currentTimeMillis()));
        loan.setDueDate(loanDto.getDueDate());
        loanRepository.save(loan);
        return new CreateLoanResponseDto(
                loan.getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getUser().getId(),
                loan.getBook().getId()
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(long id) {
        if (!loanRepository.existsById(id)) {
            throw LoanNotFound.create(id);
        }
        loanRepository.deleteById(id);
    }
    private GetLoanResponseDto mapLoan(LoanEntity loan) {
        GetUserDto user = new GetUserDto(
                loan.getUser().getId(),
                loan.getUser().getName(),
                loan.getUser().getLastName(),
                loan.getUser().getDateOfBirth(),
                loan.getUser().getEmail()
        );
        GetBookDto book = new GetBookDto(
                loan.getBook().getId(),
                loan.getBook().getIsbn(),
                loan.getBook().getTitle(),
                loan.getBook().getAuthor(),
                loan.getBook().getPublisher(),
                loan.getBook().getYearPublished(),
                loan.getBook().getAvailableCopies() > 0
        );
        return new GetLoanResponseDto(
                loan.getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                user,
                book
        );
    }
}