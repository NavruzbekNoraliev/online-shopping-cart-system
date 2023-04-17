package com.example.reports.controller;

import com.example.reports.dto.TransactionDTO;
import com.example.reports.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportsController {

    @Autowired
    TransactionService transactionService;

//    @GetMapping("/{vendorId}/sales")
//    public List<TransactionDTO> getVendorSales(@PathVariable Long vendorId) {
//        return transactionService.getSalesByVendorId(vendorId);
//    }
}
