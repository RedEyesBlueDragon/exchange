package com.example.demo.data.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyConversionListResponse {
    private List<CurrencyConversionResponse> conversions;
}
