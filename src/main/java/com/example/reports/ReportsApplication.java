package com.example.reports;

import com.example.reports.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import(CorsConfig.class)
public class ReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsApplication.class, args);
	}

}
