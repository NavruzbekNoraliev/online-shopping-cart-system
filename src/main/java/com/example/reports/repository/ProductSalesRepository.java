package com.example.reports.repository;

import com.example.reports.model.ProductSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSalesRepository extends JpaRepository<ProductSales, Long> {
    List<ProductSales> getProductSalesByVendorId(long vendorId);
}
