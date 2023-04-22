package com.example.reports.service;

import com.example.reports.model.AnalysisReport;
import com.example.reports.model.ProductSales;
import com.example.reports.model.Report;
import com.example.reports.model.Transaction;
import com.example.reports.repository.ProductSalesRepository;
import com.example.reports.repository.TransactionRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ProductSalesRepository productSalesRepository;

    @Value("${report.path}")
    private String reportPath;

    public String exportVendorSalesReport(String reportFormat, long vendorId) throws FileNotFoundException, JRException {

        List<Report> reports = new ArrayList<>();
        List<ProductSales> soldProducts = productSalesRepository.getProductSalesByVendorId(vendorId);
        for (ProductSales ps: soldProducts){
            reports.add(ps.createReport());
        }
        Map<String, Object> parameters = new HashMap<>();
        double salesTotal = reports.stream()
                .mapToDouble(r -> r.getTotal())
                .reduce(0.0, (r, element)->r + element);
        parameters.put("title", reports.get(0).getVendorName());
        parameters.put("salesTotal", salesTotal);
        String path = "classpath:templates/vendorSales.jrxml";
        String reportFileName = UUID.randomUUID() + "_" + parameters.get("title").toString();

        initilizeReport( parameters, reportFormat, path,  reports, reportPath + reportFileName);

        return reportFileName + "." + reportFormat;
    }

    public String exportUserReport(String reportFormat, long userId) throws FileNotFoundException, JRException {
        List<Report> reports = new ArrayList<>();
        List<Transaction> userOrders = transactionRepository.getTransactionByUserId(userId);
        for (Transaction ps: userOrders){
            reports.addAll(ps.createReport());
        }
        Map<String, Object> parameters = new HashMap<>();
        double salesTotal = reports.stream()
                .mapToDouble(r -> r.getTotal())
                .reduce(0.0, (r, element)->r + element);
        parameters.put("title", reports.get(0).getUserName());
        parameters.put("total", salesTotal);
        String path = "classpath:templates/userReport.jrxml";
        String reportFileName = UUID.randomUUID() + "_" + parameters.get("title").toString();

        initilizeReport( parameters, reportFormat, path,  reports, reportPath + reportFileName);

        return reportFileName + "." + reportFormat;
    }

    public String productReport(String reportFormat, long productId) throws FileNotFoundException, JRException {

        List<Report> reports = new ArrayList<>();
        List<ProductSales> soldProducts = productSalesRepository.getProductSalesByProductId(productId);
        for (ProductSales ps: soldProducts){
            reports.add(ps.createReport());
        }
        Map<String, Object> parameters = new HashMap<>();
        double salesTotal = reports.stream()
                .mapToDouble(r -> r.getTotal())
                .reduce(0.0, (r, element)->r + element);
        int quantityTotal = reports.stream()
                .mapToInt(r -> r.getQuantity())
                .reduce(0,(sum, element) -> sum + element);
        parameters.put("title", reports.get(0).getProductName());
        parameters.put("salesTotal", salesTotal);
        parameters.put("quantityTotal", quantityTotal);
        String path = "classpath:templates/productReport.jrxml";
        String reportFileName = UUID.randomUUID() + "_" + parameters.get("title").toString();

        initilizeReport( parameters, reportFormat, path,  reports, reportPath + reportFileName);

        return reportFileName + "." + reportFormat;
    }

    public String productAnalysisReport(String reportFormat, String productName) throws FileNotFoundException, JRException {

        List<AnalysisReport> reports = new ArrayList<>();
        List<ProductSales> soldProducts = productSalesRepository.getProductSalesByProductName(productName);
        Map<String, List<ProductSales>> vendorSales = new HashMap<>();
        for (ProductSales ps: soldProducts){
            if(vendorSales.get(ps.getVendorName()) == null){
                List<ProductSales> products = new ArrayList<>();
                products.add(ps);
                vendorSales.put(ps.getVendorName(), products);
            }
            else {
                List<ProductSales> products = vendorSales.get(ps.getVendorName());
                products.add(ps);
                vendorSales.put(ps.getVendorName(), products);
            }
        }
        for(Map.Entry<String, List<ProductSales>> e : vendorSales.entrySet()){
            double avg = e.getValue().stream()
                    .mapToDouble((p) -> p.getPricePerUnit())
                    .average().orElse(0.0);
            int quantity = e.getValue().stream()
                    .mapToInt((p) -> p.getQuantity())
                    .reduce(0, (acc, element) -> acc + element);
            double total = e.getValue().stream()
                    .mapToDouble((p) -> p.getTotal())
                    .reduce(0, (acc, element) -> acc + element);
            AnalysisReport ar = new AnalysisReport(e.getKey(), quantity, avg, total);
            reports.add(ar);
        }
        Map<String, Object> parameters = new HashMap<>();
        double salesTotal = reports.stream()
                .mapToDouble(r -> r.getTotal())
                .reduce(0.0, (r, element)->r + element);
        int quantityTotal = reports.stream()
                .mapToInt(r -> r.getQuantity())
                .reduce(0,(sum, element) -> sum + element);
        parameters.put("title", productName);
        String path = "classpath:templates/productSalesByVendor.jrxml";
        String reportFileName = UUID.randomUUID() + "_" + parameters.get("title").toString() + "_Analysis";

        initilizeReport( parameters, reportFormat, path,  reports, reportPath + reportFileName);

        return reportFileName + "." + reportFormat;
    }


    private static Boolean initilizeReport(Map<String, Object> parameters, String reportFormat, String path, List<?> reports, String reportFileName) throws FileNotFoundException, JRException {
        File file= ResourceUtils.getFile(path);
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportFileName + ".html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportFileName + ".pdf");
        }
        return true;
    }
}
