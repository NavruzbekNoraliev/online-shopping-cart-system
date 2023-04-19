package com.example.reports.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Report {
   private String vendorName;
   private String category;
   private String productName;
   private String userName;
   private Date date;
   private int quantity;
   private double pricePerUnit;
   private double total;

}
