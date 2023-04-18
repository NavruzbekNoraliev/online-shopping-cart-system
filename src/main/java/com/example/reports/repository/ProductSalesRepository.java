package com.example.reports.repository;

import com.example.reports.model.ProductSales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSalesRepository extends JpaRepository<ProductSales, Long> {
}
