package com.example.demo.controller;

import com.example.demo.data.Request.ConversionHistoryRequest;
import com.example.demo.data.Request.CurrencyConversionRequest;
import com.example.demo.data.Response.ConversionHistoryResponse;
import com.example.demo.data.Response.CurrencyConversionResponse;
import com.example.demo.data.Response.CurrencyRateResponse;
import com.example.demo.service.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/exchangeService")
public class ExchangeController {

    private final ExchangeService exchangeService;


    @GetMapping("/getCurrency")
    public ResponseEntity<CurrencyRateResponse> getCurrency(
            @RequestParam String baseCurrency,
            @RequestParam String targetCurrency) {
        return ResponseEntity.ok(exchangeService.getCurrencyRate(baseCurrency, targetCurrency));
    }

    @PostMapping("/convertCurrency")
    public ResponseEntity<CurrencyConversionResponse> convertCurrency(
            @RequestBody CurrencyConversionRequest request) {
        return ResponseEntity.ok(exchangeService.convertCurrency(
                request.getSourceCurrency(), request.getTargetCurrency(), request.getAmount()));

    }

    @PostMapping("/conversionHistory")
    public ResponseEntity<ConversionHistoryResponse> getHistory(@RequestBody ConversionHistoryRequest request) {
        return ResponseEntity.ok(exchangeService.getConversionHistory(request.getTransactionId(),
                request.getTransactionDate(),
                request.getPageNumber(), request.getPageSize()));
    }
}

