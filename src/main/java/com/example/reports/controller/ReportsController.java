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
@RequestMapping("/reports/")
public class ReportsController {

    @Autowired
    ReportService reportService;

    @GetMapping("/vendor/{vendorId}/sales/{format}")
    public String getVendorSales(@PathVariable String format, @PathVariable long vendorId) throws JRException, FileNotFoundException {
        return reportService.exportVendorSalesReport(format, vendorId);
    }

    @GetMapping("/user/{userId}/sales/{format}")
    public String getUserOrders(@PathVariable String format, @PathVariable long userId) throws JRException, FileNotFoundException {
        return reportService.exportUserReport(format, userId);
    }

    @GetMapping("/product/{productId}/sales/{format}")
    public String getProductSales(@PathVariable String format, @PathVariable long productId) throws JRException, FileNotFoundException {
        return reportService.productReport(format, productId);
    }

}
