package com.example.demo.service;

import com.example.demo.data.Response.ConversionHistoryResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.CurrencyRateResponse;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface ExchangeService {

    CurrencyRateResponse getCurrencyRate(String from, String to);

    CurrencyConversionResponse convertCurrency(String from, String to, Double amount);

    ConversionHistoryResponse getConversionHistory(Long transactionId, Date transactionDate, Integer page, Integer pageSize);
}
