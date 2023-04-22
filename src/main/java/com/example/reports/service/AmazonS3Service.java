package com.example.reports.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.reports.config.AmazonS3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AmazonS3Service {
    @Autowired
    private AmazonS3 amazonS3;

    public void uploadFileToS3(String bucketName, String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        amazonS3.putObject(putObjectRequest);
    }
}
