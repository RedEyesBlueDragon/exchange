package com.example.demo.service;

import com.example.demo.client.ExchangeRateFeignClient;
import com.example.demo.data.ConversionTransactionDto;
import com.example.demo.data.Response.ConversionHistoryResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.CurrencyRateResponse;
import com.example.demo.data.Response.ExchangeRateResponse;
import com.example.demo.domain.ConversionTransactionEntity;
import com.example.demo.domain.ConversionTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {


    private final ExchangeRateFeignClient exchangeRateFeignClient;

    private final String apiKey = "5dfb42be1dbff655801f82790f3a1d34";

    @Cacheable(value = "exchangeRates", key = "#from + '_' + #to", unless = "#result == null")
    @Override
    public ExchangeRateResponse getExchangeRate(String from, String to) {
        return exchangeRateFeignClient.getExchangeRate(apiKey, from + "," + to, "2");
    }


}
