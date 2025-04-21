package com.example.demo.service;

import com.example.demo.Exception.DomainException;
import com.example.demo.data.ConversionTransactionDto;
import com.example.demo.data.Response.ConversionHistoryResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.CurrencyRateResponse;
import com.example.demo.data.Response.ExchangeRateResponse;
import com.example.demo.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ExchangeServiceImpl implements ExchangeService {


    private final ExchangeRateService exchangeRateService;

    private final ConversionTransactionRepository conversionTransactionRepository;


    @Override
    public CurrencyRateResponse getCurrencyRate(String from, String to) {
        ExchangeRateResponse response = exchangeRateService.getExchangeRate(from, to);

        if (response != null
                && response.getRates() != null
                && response.getRates().get(from) != null
                && response.getRates().get(to) != null) {
            double fromRate = response.getRates().get(from);
            double toRate = response.getRates().get(to);
            return new CurrencyRateResponse(from, to, toRate / fromRate);
        }
        throw new DomainException("Unsupported currency");
    }

    @Override
    public CurrencyConversionResponse convertCurrency(String from, String to, Double amount) {
        CurrencyRateResponse currencyRateResponse = getCurrencyRate(from, to);
        if (currencyRateResponse != null) {
            double convertedAmount = amount * currencyRateResponse.getConversionRate();
            ConversionTransactionEntity conversionTransactionEntity = ConversionTransactionEntity.builder()
                    .toCurrency(to)
                    .fromCurrency(from)
                    .originalAmount(amount)
                    .convertedAmount(convertedAmount)
                    .exchangeRate(currencyRateResponse.getConversionRate())
                    .transactionTime(new Date())
                    .build();
            conversionTransactionRepository.save(conversionTransactionEntity);
            return new CurrencyConversionResponse(conversionTransactionEntity.getTransactionId(),
                    amount, convertedAmount, from, to, currencyRateResponse.getConversionRate());
        }
        throw new DomainException("Unsupported currency");
    }

    @Override
    public ConversionHistoryResponse getConversionHistory(Long transactionId, Date transactionDate,
                                                                Integer pageNumber, Integer pageSize) {
        Optional.ofNullable(pageNumber)
                .filter(s -> s >= 0)
                .orElseThrow(() -> new DomainException("Page number is invalid"));
        Optional.ofNullable(pageSize)
                .filter(s -> s > 0)
                .orElseThrow(() -> new DomainException("Page size is invalid"));

        return new ConversionHistoryResponse(Optional.ofNullable(transactionId)
                .flatMap(id -> conversionTransactionRepository.findByTransactionId(transactionId)
                        .map(s ->
                                Collections.singletonList(toDto(s))))
                .orElseGet((() -> {
                    Date startOfDay = Date.from(transactionDate.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .atStartOfDay(ZoneId.systemDefault())
                            .toInstant());

                    Date endOfDay = Date.from(transactionDate.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .atTime(LocalTime.MAX)
                            .atZone(ZoneId.systemDefault())
                            .toInstant());
                    Pageable pageable = PageRequest.of(pageNumber, pageSize);

                    return conversionTransactionRepository.findByTransactionTimeBetween(startOfDay, endOfDay, pageable)
                            .getContent()
                            .stream().map(this::toDto)
                            .collect(Collectors.toList());
                })),
                pageNumber,
                pageSize);
    }

    private ConversionTransactionDto toDto(ConversionTransactionEntity entity) {
        return ConversionTransactionDto.builder()
                .transactionId(entity.getTransactionId())
                .originalAmount(entity.getOriginalAmount())
                .convertedAmount(entity.getConvertedAmount())
                .sourceCurrency(entity.getFromCurrency())
                .targetCurrency(entity.getToCurrency())
                .transactionTime(entity.getTransactionTime())
                .build();
    }
}
