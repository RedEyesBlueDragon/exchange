package com.example.demo.service;

import com.example.demo.Exception.DomainException;
import com.example.demo.client.ExchangeRateFeignClient;
import com.example.demo.data.Response.CurrencyConversionListResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.ExchangeRateResponse;
import com.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class BulkCurrencyConversionServiceImpl implements BulkCurrencyConversionService {

    private final ExchangeService exchangeService;

    @Override
    public CurrencyConversionListResponse processCsvFile(MultipartFile file) {
        List<CurrencyConversionResponse> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVReader csvReader = new CSVReader(reader)) {

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                try {
                    String from = line[0];
                    String to = line[1];
                    Double amount = Double.parseDouble(line[2]);
                    CurrencyConversionResponse response = exchangeService.convertCurrency(from, to, amount);
                    results.add(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            throw new DomainException("Failed to process CSV file: " + e);
        }

        return new CurrencyConversionListResponse(results);
    }
}
