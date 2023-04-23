package com.example.payment.paymentservice.repository;

import com.example.payment.paymentservice.model.entity.VendorRevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendorRevenueRepository extends JpaRepository<VendorRevenueEntity, Long> {
    VendorRevenueEntity findByVendorId(Long vendorId);

}
