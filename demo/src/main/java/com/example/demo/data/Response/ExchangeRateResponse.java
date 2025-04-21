package com.example.demo.data.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeRateResponse {
    private boolean success;
    private String base;
    private String date;
    private Map<String, Double> rates;
}
