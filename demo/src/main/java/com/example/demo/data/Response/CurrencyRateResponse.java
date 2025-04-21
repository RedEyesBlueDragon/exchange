package com.example.demo.data.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyRateResponse {
    private String sourceCurrency;
    private String targetCurrency;
    private Double conversionRate;
}
