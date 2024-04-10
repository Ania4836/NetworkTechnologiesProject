package com.example.anianetworkproject.infrastructure.repository;

import com.example.anianetworkproject.infrastructure.entity.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    Optional<LoanEntity> findByBookIdAndUserId(long bookId, long userId);

    List<LoanEntity> findByUserId(long userId);
    Page<LoanEntity> findByUserId(long userId, Pageable pageable);
}