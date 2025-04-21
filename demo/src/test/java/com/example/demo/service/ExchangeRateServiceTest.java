package com.example.demo.service;

import com.example.demo.client.ExchangeRateFeignClient;
import com.example.demo.data.Response.ExchangeRateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {

    @Mock
    private ExchangeRateFeignClient exchangeRateFeignClient;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Test
    void givenFromAndTo_whenGetExchangeRate_thenReturnExchangeRate() {
        given(exchangeRateFeignClient.getExchangeRate(any(), eq("EUR,USD"), eq("2")))
                .willReturn(new ExchangeRateResponse());
        ExchangeRateResponse exchangeRate = exchangeRateService.getExchangeRate("EUR", "USD");
        Assertions.assertNotNull(exchangeRate);
    }
}
