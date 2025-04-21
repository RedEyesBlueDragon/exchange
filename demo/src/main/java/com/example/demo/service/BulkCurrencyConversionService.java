package com.example.demo.service;

import com.example.demo.data.Response.CurrencyConversionListResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.ExchangeRateResponse;
import org.springframework.web.multipart.MultipartFile;

public interface BulkCurrencyConversionService {

    public CurrencyConversionListResponse processCsvFile(MultipartFile file);
}
