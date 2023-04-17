package com.example.reports.dto;

import com.example.reports.model.ProductSales;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDTO {

    List<ProductSales> productSales;
    Date date;
    long userId;
}
