package com.example.demo.service;

import com.example.demo.data.Response.CurrencyConversionListResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class BulkCurrencyConversionServiceTest {

    @Mock
    ExchangeServiceImpl exchangeService;

    @InjectMocks
    BulkCurrencyConversionServiceImpl bulkCurrencyConversionService;

    @Test
    void givenValidCsvFile_whenProcessCsvFile_thenReturnCurrencyConversionListResponse() throws Exception {
        String csvContent = "EUR,USD,100";
        MultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

        CurrencyConversionResponse conversionResponse = new CurrencyConversionResponse(1L, 100.0, 120.0, "EUR", "USD", 1.2);

        given(exchangeService.convertCurrency("EUR", "USD", 100.0)).willReturn(conversionResponse);

        CurrencyConversionListResponse result = bulkCurrencyConversionService.processCsvFile(file);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getConversions().size());
        Assertions.assertEquals("EUR", result.getConversions().get(0).getSourceCurrency());
        Assertions.assertEquals("USD", result.getConversions().get(0).getTargetCurrency());
    }


}
