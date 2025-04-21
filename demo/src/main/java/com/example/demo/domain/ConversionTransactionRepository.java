package com.example.demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConversionTransactionRepository extends JpaRepository<ConversionTransactionEntity, Long> {

    List<ConversionTransactionEntity> findAllByTransactionId(Long transactionId);

    <S extends ConversionTransactionEntity> S save(S entity);

    Optional<ConversionTransactionEntity> findByTransactionId(Long transactionId);

    Page<ConversionTransactionEntity> findByTransactionTimeBetween(
            Date start, Date end, Pageable pageable
    );

}

