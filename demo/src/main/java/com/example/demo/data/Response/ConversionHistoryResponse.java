package com.example.demo.data.Response;

import com.example.demo.data.ConversionTransactionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConversionHistoryResponse {
    private List<ConversionTransactionDto> conversions;
    private int pageNumber;
    private int pageSize;
}
