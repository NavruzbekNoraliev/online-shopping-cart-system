package com.example.reports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisReport {
    String vendorName;
    int quantity;
    double avgPrice;
    double total;
}
