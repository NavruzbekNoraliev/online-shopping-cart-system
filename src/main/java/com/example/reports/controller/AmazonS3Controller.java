package com.example.reports.controller;

import com.example.reports.service.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/s3")
public class AmazonS3Controller {

    @Autowired
    AmazonS3Service amazonS3Service;

    @Value("${report.path}")
    private String reportPath;

    @Value("${aws.s3.reportPath}")
    private String s3Path;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${report.returnUrl}")
    private String returnUrl;

    @GetMapping("/{fileName}")
    public String getLink(@PathVariable String fileName){
        amazonS3Service.uploadFileToS3(bucketName, fileName, new File(reportPath + fileName));
        return returnUrl + fileName;
    }
}
