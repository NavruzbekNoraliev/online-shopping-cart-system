package com.example.reports.service;

import com.example.reports.model.ProductSales;
import com.example.reports.repository.ProductSalesRepository;
import com.example.reports.repository.TransactionRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ProductSalesRepository productSalesRepository;
    public String exportVendorSalesReport(String reportFormat, long vendorId) throws FileNotFoundException, JRException {
        List<ProductSales> soldProducts = productSalesRepository.getProductSalesByVendorId(vendorId);
        File file= ResourceUtils.getFile("classpath:vendorSales.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(soldProducts);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", soldProducts.get(0).getVendorName());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
        }
        return "report generated";
    }
}
