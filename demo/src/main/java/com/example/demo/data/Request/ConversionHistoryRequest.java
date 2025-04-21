package com.example.demo.data.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConversionHistoryRequest {
    private Long transactionId;
    private Date transactionDate;
    private int pageNumber;
    private int pageSize;
}
