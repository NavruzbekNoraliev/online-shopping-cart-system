package com.example.reports.controller;

import com.example.reports.dto.TransactionDTO;
import com.example.reports.service.ReportService;
import com.example.reports.service.TransactionService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ReportsController {

    @Autowired
    ReportService reportService;

    @GetMapping("/{vendorId}/sales/{format}")
    public String getVendorSales(@PathVariable String format, @PathVariable Long vendorId) throws JRException, FileNotFoundException {
        return reportService.exportVendorSalesReport(format, vendorId);
    }
}
