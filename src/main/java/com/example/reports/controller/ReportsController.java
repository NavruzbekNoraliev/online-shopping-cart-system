package com.example.reports.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.reports.dto.TransactionDTO;
import com.example.reports.service.AmazonS3Service;
import com.example.reports.service.ReportService;
import com.example.reports.service.TransactionService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {

    @Autowired
    ReportService reportService;

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${report.path}")
    private String reportPath;

    @Value("${report.returnUrl}")
    private String returnUrl;

    @GetMapping("/vendor/{vendorId}/sales/{format}")
    public String getVendorSales(@PathVariable String format, @PathVariable long vendorId) throws JRException, IOException, InterruptedException {

        String fileName = reportService.exportVendorSalesReport(format, vendorId);
        File file = new File(reportPath + fileName);
        amazonS3Service.uploadFileToS3(bucketName, fileName, file);

        return returnUrl + fileName;
    }

    @GetMapping("/user/{userId}/sales/{format}")
    public String getUserOrders(@PathVariable String format, @PathVariable long userId) throws JRException, FileNotFoundException {
        String fileName =  reportService.exportUserReport(format, userId);
        File file = new File(reportPath + fileName);
        amazonS3Service.uploadFileToS3(bucketName, fileName, file);

        return returnUrl + fileName;
    }

    @GetMapping("/product/{productId}/sales/{format}")
    public String getProductSales(@PathVariable String format, @PathVariable long productId) throws JRException, FileNotFoundException {
        String fileName = reportService.productReport(format, productId);
        File file = new File(reportPath + fileName);
        amazonS3Service.uploadFileToS3(bucketName, fileName, file);

        return returnUrl + fileName;
    }

    @GetMapping("/analysis/product/{productName}/sales/{format}")
    public String getProductSalesAnalysis(@PathVariable String format, @PathVariable String productName) throws JRException, FileNotFoundException {
        String fileName =  reportService.productAnalysisReport(format, productName);
        File file = new File(reportPath + fileName);
        amazonS3Service.uploadFileToS3(bucketName, fileName, file);

        return returnUrl + fileName;
    }

}
