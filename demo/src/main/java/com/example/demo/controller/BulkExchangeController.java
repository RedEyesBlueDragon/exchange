package com.example.demo.controller;

import com.example.demo.data.Request.ConversionHistoryRequest;
import com.example.demo.data.Request.CurrencyConversionRequest;
import com.example.demo.data.Response.ConversionHistoryResponse;
import com.example.demo.data.Response.CurrencyConversionListResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.CurrencyRateResponse;
import com.example.demo.service.BulkCurrencyConversionService;
import com.example.demo.service.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bulkExchangeService")
public class BulkExchangeController {

    private final BulkCurrencyConversionService bulkCurrencyConversionService;


    @PostMapping("/convertCurrency")
    public ResponseEntity<CurrencyConversionListResponse> bulkConvert(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(bulkCurrencyConversionService.processCsvFile(file));
    }

}

