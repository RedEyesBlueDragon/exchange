package com.example.demo.data.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyConversionResponse {
    private Long transactionId;
    private double originalAmount;
    private double conversionAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private Double conversionRate;
}
