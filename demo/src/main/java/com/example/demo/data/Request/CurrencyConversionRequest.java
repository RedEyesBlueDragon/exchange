package com.example.demo.data.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyConversionRequest {
    private double amount;
    private String sourceCurrency;
    private String targetCurrency;
}
