package com.example.demo.client;

import com.example.demo.data.Response.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchangeRateClient", url = "https://data.fixer.io/api")
public interface ExchangeRateFeignClient {


    @GetMapping("/latest")
    ExchangeRateResponse getExchangeRate(@RequestParam("access_key") String apiKey,
                                         @RequestParam("symbols") String symbols,
                                         @RequestParam("format") String format);
}
