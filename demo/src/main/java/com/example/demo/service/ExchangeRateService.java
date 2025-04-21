package com.example.demo.service;

import com.example.demo.data.Response.ConversionHistoryResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.CurrencyRateResponse;
import com.example.demo.data.Response.ExchangeRateResponse;

import java.util.Date;

public interface ExchangeRateService {

    ExchangeRateResponse getExchangeRate(String from, String to);

}
