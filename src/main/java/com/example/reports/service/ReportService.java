package com.example.reports.service;

import com.example.reports.model.ProductSales;
import com.example.reports.model.Report;
import com.example.reports.model.Transaction;
import com.example.reports.repository.ProductSalesRepository;
import com.example.reports.repository.TransactionRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        String path = "classpath:vendorSales.jrxml";
        String reportFileName = parameters.get("title").toString();

        initilizeReport( parameters, reportFormat, path,  reports, reportFileName);


//            File file= ResourceUtils.getFile("classpath:vendorSales.jrxml");
//        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")){
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")){
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
//        }

        return "vendor report generated";
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
        String path = "classpath:userReport.jrxml";
        String reportFileName = parameters.get("title").toString();

        initilizeReport( parameters, reportFormat, path,  reports, reportFileName);

//        File file= ResourceUtils.getFile("classpath:userReport.jrxml");
//        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")){
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")){
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
//        }
        return "user report generated";
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
        String path = "classpath:productReport.jrxml";
        String reportFileName = parameters.get("title").toString();

        initilizeReport( parameters, reportFormat, path,  reports, reportFileName);

//        File file= ResourceUtils.getFile("classpath:productReport.jrxml");
//        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")){
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")){
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
//        }
        return "product report generated";
    }

    public String productAnalysisReport(String reportFormat, String productName) throws FileNotFoundException, JRException {

        List<Report> reports = new ArrayList<>();
        List<ProductSales> soldProducts = productSalesRepository.getProductSalesByProductName(productName);
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
        parameters.put("title", reports.get(0).getVendorName());
        parameters.put("salesTotal", salesTotal);
        parameters.put("quantityTotal", quantityTotal);
        String path = "classpath:userReport.jrxml";
        String reportFileName = parameters.get("title").toString();

        initilizeReport( parameters, reportFormat, path,  reports, reportFileName);

//        File file= ResourceUtils.getFile("classpath:productSalesByVendor.jrxml");
//        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")){
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, "report.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")){
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
//        }
        return "product analysis report generated";
    }



    private static Boolean initilizeReport(Map<String, Object> parameters, String reportFormat, String path, List<Report> reports, String reportFileName) throws FileNotFoundException, JRException {
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
