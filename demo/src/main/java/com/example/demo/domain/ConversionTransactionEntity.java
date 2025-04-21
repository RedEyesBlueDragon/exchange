package com.example.demo.domain;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Conversion_Transaction")
public class ConversionTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID", unique = true)
    private Long transactionId;

    @Column(name = "FROM_CURRENCY", nullable = false)
    private String fromCurrency;

    @Column(name = "TO_CURRENCY", nullable = false)
    private String toCurrency;

    @Column(name = "ORIGINAL_AMOUNT", nullable = false)
    private Double originalAmount;

    @Column(name = "CONVERTED_AMOUNT", nullable = false)
    private Double convertedAmount;

    @Column(name = "EXCHANGE_RATE", nullable = false)
    private Double exchangeRate;

    @Column(name = "TRANSACTION_TIME", nullable = false)
    private Date transactionTime;
}

