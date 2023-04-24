package com.order;

import com.order.Configuration.CorsConfig;
import com.order.Service.DTO.VendorDTO;
import com.order.Service.Impl.GetVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@EnableFeignClients
@Import(CorsConfig.class)
public class OrderApplication{
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
