package com.example.demo.service;

import com.example.demo.Exception.DomainException;
import com.example.demo.data.Response.ConversionHistoryResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.CurrencyRateResponse;
import com.example.demo.data.Response.ExchangeRateResponse;
import com.example.demo.domain.ConversionTransactionEntity;
import com.example.demo.domain.ConversionTransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ExchangeServiceTest {


    @Mock
    ExchangeRateServiceImpl exchangeRateService;

    @Mock
    ConversionTransactionRepository conversionTransactionRepository;

    @InjectMocks
    ExchangeServiceImpl exchangeService;


    @Test
    void givenValidFromAndTo_whenGetCurrencyRate_thenReturnCurrencyRate() {
        ExchangeRateResponse rateResponse = new ExchangeRateResponse();
        rateResponse.setRates(Map.of("EUR", 1.0, "USD", 1.2));
        given(exchangeRateService.getExchangeRate("EUR", "USD")).willReturn(rateResponse);

        CurrencyRateResponse currencyRate = exchangeService.getCurrencyRate("EUR", "USD");

        Assertions.assertNotNull(currencyRate);
        Assertions.assertEquals("EUR", currencyRate.getSourceCurrency());
        Assertions.assertEquals("USD", currencyRate.getTargetCurrency());
        Assertions.assertEquals(1.2, currencyRate.getConversionRate());
    }

    @Test
    void givenInvalidFromAndTo_whenGetCurrencyRate_thenThrowDomainException() {
        ExchangeRateResponse rateResponse = new ExchangeRateResponse();
        rateResponse.setRates(Map.of("EUR", 1.0));
        given(exchangeRateService.getExchangeRate("EUR", "USD")).willReturn(rateResponse);

        Assertions.assertThrows(DomainException.class, () -> exchangeService.getCurrencyRate("EUR", "USD"));
    }

    @Test
    void givenValidFromToAmount_whenConvertCurrency_thenReturnCurrencyConversionResponse() {
        ExchangeRateResponse rateResponse = new ExchangeRateResponse();
        rateResponse.setRates(Map.of("EUR", 1.0, "USD", 1.2));
        given(exchangeRateService.getExchangeRate("EUR", "USD")).willReturn(rateResponse);

        double amount = 100.0;
        CurrencyConversionResponse response = exchangeService.convertCurrency("EUR", "USD", amount);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(amount, response.getOriginalAmount());
        Assertions.assertEquals(amount * 1.2, response.getConversionAmount());
        verify(conversionTransactionRepository, times(1)).save(any());
    }

    @Test
    void givenValidTransactionIdAndDate_whenGetConversionHistory_thenReturnConversionHistoryResponse() {
        Date transactionDate = new Date();
        given(conversionTransactionRepository.findByTransactionId(1L))
                .willReturn(Optional.of(new ConversionTransactionEntity(1L, "EUR", "USD", 100.0, 120.0, 1.2, transactionDate)));

        ConversionHistoryResponse response = exchangeService.getConversionHistory(1L, transactionDate, 0, 10);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getConversions().isEmpty());
        Assertions.assertEquals(1L, response.getConversions().get(0).getTransactionId());
    }

}
